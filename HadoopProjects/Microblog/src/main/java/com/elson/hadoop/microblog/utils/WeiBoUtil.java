package com.elson.hadoop.microblog.utils;

import com.elson.hadoop.microblog.constant.Constant;
import org.apache.hadoop.hbase.*;
import org.apache.hadoop.hbase.client.*;
import org.apache.hadoop.hbase.filter.CompareFilter;
import org.apache.hadoop.hbase.filter.RowFilter;
import org.apache.hadoop.hbase.filter.SubstringComparator;
import org.apache.hadoop.hbase.util.Bytes;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class WeiBoUtil {

    //创建命名空间
    public static void createNamespace(String ns) throws IOException {

        //创建连接
        Connection connection = HBaseConn.getHBaseConn();
        Admin admin = connection.getAdmin();

        //创建NS描述器
        NamespaceDescriptor namespaceDescriptor = NamespaceDescriptor.create(ns).build();

        //创建操作
        admin.createNamespace(namespaceDescriptor);

        //关闭资源
        admin.close();
        connection.close();
    }


    //创建表
    public static void createTable(String tableName, int versions, String... cfs) throws IOException {

        //创建连接
        Connection connection = HBaseConn.getHBaseConn();
        Admin admin = connection.getAdmin();

        //创建表描述器
        HTableDescriptor hTableDescriptor = new HTableDescriptor(TableName.valueOf(tableName));

        //循环添加列族
        for (String cf : cfs) {
            HColumnDescriptor hColumnDescriptor = new HColumnDescriptor(cf);
            hColumnDescriptor.setMaxVersions(versions);
            hTableDescriptor.addFamily(hColumnDescriptor);
        }

        //创建表操作
        admin.createTable(hTableDescriptor);

        //关闭资源
        admin.close();
        connection.close();

    }


    /**
     * 1.更新微博内容表数据
     * 2.更新收件箱表的数据
     * --获取当前操作人的fans
     * --去往收件箱表依次更新数据
     *
     * @param uid
     * @param content
     * @throws IOException
     */


    //发布微博
    public static void createDate(String uid, String content) throws IOException {

        //获取连接
        Connection connection = HBaseConn.getHBaseConn();

        //获取三张操作表的对象
        Table contTable = connection.getTable(TableName.valueOf(Constant.CONTENT));
        Table relaTable = connection.getTable(TableName.valueOf(Constant.RELATIONS));
        Table inboxTable = connection.getTable(TableName.valueOf(Constant.INBOX));

        //拼接RK
        long ts = System.currentTimeMillis();
        String rowKey = uid + "_" + ts;

        //生成Put对象
        Put put = new Put(Bytes.toBytes(rowKey));
        put.addColumn(Bytes.toBytes("info"), Bytes.toBytes("content"), Bytes.toBytes(content));

        //往内容表添加数据
        contTable.put(put);

        //获取关系表中的fans
        Get get = new Get(Bytes.toBytes(uid));
        get.addFamily(Bytes.toBytes("fans"));
        Result result = relaTable.get(get);
        Cell[] cells = result.rawCells();

        if (cells.length <= 0) {
            return;
        }
        //更新fans收件箱表
        List<Put> puts = new ArrayList<Put>();
        for (Cell cell : cells) {
            byte[] cloneQualifier = CellUtil.cloneQualifier(cell);
            Put inboxPut = new Put(cloneQualifier);
            inboxPut.addColumn(Bytes.toBytes("info"), Bytes.toBytes(uid), ts, Bytes.toBytes(rowKey));
            puts.add(inboxPut);

        }
        inboxTable.put(puts);

        //关闭资源
        inboxTable.close();
        relaTable.close();
        contTable.close();

        connection.close();

    }


    /**
     * 1.在用户关系表
     * --添加操作人的attends
     * --添加被操作人的fans
     * 2.在收件箱中
     * --在微博内容表中获取被关注者的3条数据（rowkey）
     * --在收件箱表中添加操作人的关注者信息
     *
     * @param uid
     * @param uids
     */


    //关注用户
    public static void addAttend(String uid, String... uids) throws IOException {

        //获取连接
        Connection connection = HBaseConn.getHBaseConn();

        //获取三张操作表的对象
        Table contTable = connection.getTable(TableName.valueOf(Constant.CONTENT));
        Table relaTable = connection.getTable(TableName.valueOf(Constant.RELATIONS));
        Table inboxTable = connection.getTable(TableName.valueOf(Constant.INBOX));

        //创建操作者的Put对象
        Put relaPut = new Put(Bytes.toBytes(uid));

        ArrayList<Put> puts = new ArrayList<>();

        for (String s : uids) {
            relaPut.addColumn(Bytes.toBytes("attends"), Bytes.toBytes(s), Bytes.toBytes(s));

            //创建被关注者的Put对象
            Put fansPut = new Put(Bytes.toBytes(s));
            fansPut.addColumn(Bytes.toBytes("fans"), Bytes.toBytes(uid), Bytes.toBytes(uid));
            puts.add(fansPut); //添加被操作人的fans
        }

        puts.add(relaPut); //添加操作人的attends

        relaTable.put(puts);


        Put inboxPut = new Put(Bytes.toBytes(uid));
        //获取内容表中被关注者的rowkey
        for (String s : uids) {
            Scan scan = new Scan(Bytes.toBytes(s), Bytes.toBytes(s + "|"));
            ResultScanner results = contTable.getScanner(scan);

            for (Result result : results) {
                String rowKey = Bytes.toString(result.getRow());
                String[] split = rowKey.split("_");
                byte[] row = result.getRow();
                inboxPut.addColumn(Bytes.toBytes("info"), Bytes.toBytes(s), Long.parseLong(split[1]), row);

            }

        }

        inboxTable.put(inboxPut);

        inboxTable.close();
        relaTable.close();
        contTable.close();

        connection.close();

    }


    /**
     * 1.用户关系表
     * --删除操作者关注列族的待取关用户
     * --删除待取关用户fans列族的操作者
     * <p>
     * 2.收件箱表
     * --删除操作者的待取关用户信息
     **/
    //取关用户
    public static void delAttend(String uid, String... uids) throws IOException {

        //获取连接
        Connection connection = HBaseConn.getHBaseConn();

        //获取表对象
        Table relaTable = connection.getTable(TableName.valueOf(Constant.RELATIONS));
        Table inboxTable = connection.getTable(TableName.valueOf(Constant.INBOX));

        //创建操作者的删除对象
        Delete relaDel = new Delete(Bytes.toBytes(uid));

        ArrayList<Delete> deletes = new ArrayList<Delete>();

        for (String s : uids) {

            //创建被取关者删除对象
            Delete fansDel = new Delete(Bytes.toBytes(s));
            fansDel.addColumns(Bytes.toBytes("fans"), Bytes.toBytes(uid));
            deletes.add(fansDel);

            relaDel.addColumns(Bytes.toBytes("attends"), Bytes.toBytes(s));
        }
        deletes.add(relaDel);

        //执行删除操作
        relaTable.delete(deletes);


        //删除收件箱表的相关内容
        Delete inboxDel = new Delete(Bytes.toBytes(uid));
        for (String s : uids) {
            inboxDel.addColumns(Bytes.toBytes("info"), Bytes.toBytes(s));
        }

        //执行收件箱表删除操作
        inboxTable.delete(inboxDel);

        //关闭资源
        inboxTable.close();
        relaTable.close();

        connection.close();
    }


    //获取微博内容（初始化页面）
    public static void getInit(String uid) throws IOException {

        //获取连接
        Connection connection = HBaseConn.getHBaseConn();


        //获取表对象（2个）
        Table inboxTable = connection.getTable(TableName.valueOf(Constant.INBOX));
        Table contTable = connection.getTable(TableName.valueOf(Constant.CONTENT));


        //获取收件箱表数据
        Get get = new Get(Bytes.toBytes(uid)); //收件箱表get对象
        get.setMaxVersions(); //设置获取最大版本的数据

        Result result = inboxTable.get(get);

        ArrayList<Get> gets = new ArrayList<Get>();

        Cell[] cells = result.rawCells();
        //遍历返回内容并将其封装成内容的get对象
        for (Cell cell : cells) {
            Get contGet = new Get(CellUtil.cloneValue(cell));
            gets.add(contGet);
        }


        //根据收件箱表获取值去往内容表获取实际微博内容
        Result[] results = contTable.get(gets);
        for (Result result1 : results) {
            Cell[] cells1 = result1.rawCells();
            //遍历并打印
            for (Cell cell : cells1) {
                System.out.println("RK:" + Bytes.toString(CellUtil.cloneRow(cell)) + ",Content:" + Bytes.toString(CellUtil.cloneValue(cell)));
            }
        }


        //关闭资源
        inboxTable.close();
        contTable.close();

        connection.close();
    }


    //获取微博内容（查看某个人所有内容）
    public static void getData(String uid) throws IOException {

        //获取连接
        Connection connection = HBaseConn.getHBaseConn();

        //获取表对象
        Table table = connection.getTable(TableName.valueOf(Constant.CONTENT));

        //扫描（过滤器）
        Scan scan = new Scan();

//        过滤器集合
//        FilterList filterList = new FilterList(FilterList.Operator.MUST_PASS_ALL);
//        filterList.addFilter();

        RowFilter rowFilter = new RowFilter(CompareFilter.CompareOp.EQUAL, new SubstringComparator(uid + "_"));

        scan.setFilter(rowFilter);
        ResultScanner results = table.getScanner(scan);

        //遍历打印
        for (Result result : results) {
            Cell[] cells = result.rawCells();
            for (Cell cell : cells) {
                System.out.println("RK:" + Bytes.toString(CellUtil.cloneRow(cell)) + ",Content:" + Bytes.toString(CellUtil.cloneValue(cell)));
            }
        }

        //关闭资源
        table.close();

        connection.close();

    }

}

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

    //���������ռ�
    public static void createNamespace(String ns) throws IOException {

        //��������
        Connection connection = HBaseConn.getHBaseConn();
        Admin admin = connection.getAdmin();

        //����NS������
        NamespaceDescriptor namespaceDescriptor = NamespaceDescriptor.create(ns).build();

        //��������
        admin.createNamespace(namespaceDescriptor);

        //�ر���Դ
        admin.close();
        connection.close();
    }


    //������
    public static void createTable(String tableName, int versions, String... cfs) throws IOException {

        //��������
        Connection connection = HBaseConn.getHBaseConn();
        Admin admin = connection.getAdmin();

        //������������
        HTableDescriptor hTableDescriptor = new HTableDescriptor(TableName.valueOf(tableName));

        //ѭ���������
        for (String cf : cfs) {
            HColumnDescriptor hColumnDescriptor = new HColumnDescriptor(cf);
            hColumnDescriptor.setMaxVersions(versions);
            hTableDescriptor.addFamily(hColumnDescriptor);
        }

        //���������
        admin.createTable(hTableDescriptor);

        //�ر���Դ
        admin.close();
        connection.close();

    }


    /**
     * 1.����΢�����ݱ�����
     * 2.�����ռ���������
     * --��ȡ��ǰ�����˵�fans
     * --ȥ���ռ�������θ�������
     *
     * @param uid
     * @param content
     * @throws IOException
     */


    //����΢��
    public static void createDate(String uid, String content) throws IOException {

        //��ȡ����
        Connection connection = HBaseConn.getHBaseConn();

        //��ȡ���Ų�����Ķ���
        Table contTable = connection.getTable(TableName.valueOf(Constant.CONTENT));
        Table relaTable = connection.getTable(TableName.valueOf(Constant.RELATIONS));
        Table inboxTable = connection.getTable(TableName.valueOf(Constant.INBOX));

        //ƴ��RK
        long ts = System.currentTimeMillis();
        String rowKey = uid + "_" + ts;

        //����Put����
        Put put = new Put(Bytes.toBytes(rowKey));
        put.addColumn(Bytes.toBytes("info"), Bytes.toBytes("content"), Bytes.toBytes(content));

        //�����ݱ��������
        contTable.put(put);

        //��ȡ��ϵ���е�fans
        Get get = new Get(Bytes.toBytes(uid));
        get.addFamily(Bytes.toBytes("fans"));
        Result result = relaTable.get(get);
        Cell[] cells = result.rawCells();

        if (cells.length <= 0) {
            return;
        }
        //����fans�ռ����
        List<Put> puts = new ArrayList<Put>();
        for (Cell cell : cells) {
            byte[] cloneQualifier = CellUtil.cloneQualifier(cell);
            Put inboxPut = new Put(cloneQualifier);
            inboxPut.addColumn(Bytes.toBytes("info"), Bytes.toBytes(uid), ts, Bytes.toBytes(rowKey));
            puts.add(inboxPut);

        }
        inboxTable.put(puts);

        //�ر���Դ
        inboxTable.close();
        relaTable.close();
        contTable.close();

        connection.close();

    }


    /**
     * 1.���û���ϵ��
     * --��Ӳ����˵�attends
     * --��ӱ������˵�fans
     * 2.���ռ�����
     * --��΢�����ݱ��л�ȡ����ע�ߵ�3�����ݣ�rowkey��
     * --���ռ��������Ӳ����˵Ĺ�ע����Ϣ
     *
     * @param uid
     * @param uids
     */


    //��ע�û�
    public static void addAttend(String uid, String... uids) throws IOException {

        //��ȡ����
        Connection connection = HBaseConn.getHBaseConn();

        //��ȡ���Ų�����Ķ���
        Table contTable = connection.getTable(TableName.valueOf(Constant.CONTENT));
        Table relaTable = connection.getTable(TableName.valueOf(Constant.RELATIONS));
        Table inboxTable = connection.getTable(TableName.valueOf(Constant.INBOX));

        //���������ߵ�Put����
        Put relaPut = new Put(Bytes.toBytes(uid));

        ArrayList<Put> puts = new ArrayList<>();

        for (String s : uids) {
            relaPut.addColumn(Bytes.toBytes("attends"), Bytes.toBytes(s), Bytes.toBytes(s));

            //��������ע�ߵ�Put����
            Put fansPut = new Put(Bytes.toBytes(s));
            fansPut.addColumn(Bytes.toBytes("fans"), Bytes.toBytes(uid), Bytes.toBytes(uid));
            puts.add(fansPut); //��ӱ������˵�fans
        }

        puts.add(relaPut); //��Ӳ����˵�attends

        relaTable.put(puts);


        Put inboxPut = new Put(Bytes.toBytes(uid));
        //��ȡ���ݱ��б���ע�ߵ�rowkey
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
     * 1.�û���ϵ��
     * --ɾ�������߹�ע����Ĵ�ȡ���û�
     * --ɾ����ȡ���û�fans����Ĳ�����
     * <p>
     * 2.�ռ����
     * --ɾ�������ߵĴ�ȡ���û���Ϣ
     **/
    //ȡ���û�
    public static void delAttend(String uid, String... uids) throws IOException {

        //��ȡ����
        Connection connection = HBaseConn.getHBaseConn();

        //��ȡ�����
        Table relaTable = connection.getTable(TableName.valueOf(Constant.RELATIONS));
        Table inboxTable = connection.getTable(TableName.valueOf(Constant.INBOX));

        //���������ߵ�ɾ������
        Delete relaDel = new Delete(Bytes.toBytes(uid));

        ArrayList<Delete> deletes = new ArrayList<Delete>();

        for (String s : uids) {

            //������ȡ����ɾ������
            Delete fansDel = new Delete(Bytes.toBytes(s));
            fansDel.addColumns(Bytes.toBytes("fans"), Bytes.toBytes(uid));
            deletes.add(fansDel);

            relaDel.addColumns(Bytes.toBytes("attends"), Bytes.toBytes(s));
        }
        deletes.add(relaDel);

        //ִ��ɾ������
        relaTable.delete(deletes);


        //ɾ���ռ������������
        Delete inboxDel = new Delete(Bytes.toBytes(uid));
        for (String s : uids) {
            inboxDel.addColumns(Bytes.toBytes("info"), Bytes.toBytes(s));
        }

        //ִ���ռ����ɾ������
        inboxTable.delete(inboxDel);

        //�ر���Դ
        inboxTable.close();
        relaTable.close();

        connection.close();
    }


    //��ȡ΢�����ݣ���ʼ��ҳ�棩
    public static void getInit(String uid) throws IOException {

        //��ȡ����
        Connection connection = HBaseConn.getHBaseConn();


        //��ȡ�����2����
        Table inboxTable = connection.getTable(TableName.valueOf(Constant.INBOX));
        Table contTable = connection.getTable(TableName.valueOf(Constant.CONTENT));


        //��ȡ�ռ��������
        Get get = new Get(Bytes.toBytes(uid)); //�ռ����get����
        get.setMaxVersions(); //���û�ȡ���汾������

        Result result = inboxTable.get(get);

        ArrayList<Get> gets = new ArrayList<Get>();

        Cell[] cells = result.rawCells();
        //�����������ݲ������װ�����ݵ�get����
        for (Cell cell : cells) {
            Get contGet = new Get(CellUtil.cloneValue(cell));
            gets.add(contGet);
        }


        //�����ռ�����ȡֵȥ�����ݱ��ȡʵ��΢������
        Result[] results = contTable.get(gets);
        for (Result result1 : results) {
            Cell[] cells1 = result1.rawCells();
            //��������ӡ
            for (Cell cell : cells1) {
                System.out.println("RK:" + Bytes.toString(CellUtil.cloneRow(cell)) + ",Content:" + Bytes.toString(CellUtil.cloneValue(cell)));
            }
        }


        //�ر���Դ
        inboxTable.close();
        contTable.close();

        connection.close();
    }


    //��ȡ΢�����ݣ��鿴ĳ�����������ݣ�
    public static void getData(String uid) throws IOException {

        //��ȡ����
        Connection connection = HBaseConn.getHBaseConn();

        //��ȡ�����
        Table table = connection.getTable(TableName.valueOf(Constant.CONTENT));

        //ɨ�裨��������
        Scan scan = new Scan();

//        ����������
//        FilterList filterList = new FilterList(FilterList.Operator.MUST_PASS_ALL);
//        filterList.addFilter();

        RowFilter rowFilter = new RowFilter(CompareFilter.CompareOp.EQUAL, new SubstringComparator(uid + "_"));

        scan.setFilter(rowFilter);
        ResultScanner results = table.getScanner(scan);

        //������ӡ
        for (Result result : results) {
            Cell[] cells = result.rawCells();
            for (Cell cell : cells) {
                System.out.println("RK:" + Bytes.toString(CellUtil.cloneRow(cell)) + ",Content:" + Bytes.toString(CellUtil.cloneValue(cell)));
            }
        }

        //�ر���Դ
        table.close();

        connection.close();

    }

}

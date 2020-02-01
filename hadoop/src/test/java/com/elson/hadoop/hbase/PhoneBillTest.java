package com.elson.hadoop.hbase;

import com.elson.hadoop.utils.HBaseUtil;
import org.apache.hadoop.hbase.client.*;
import org.apache.hadoop.hbase.util.Bytes;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class PhoneBillTest {

    private static String tableName = "t_cdr";

    @Before
    public void init() {
        System.setProperty("hadoop.home.dir", "/usr/local/java");
    }

    @Test
    public void testCreateTable() throws Exception {
     /*Configuration config = HBaseConfiguration.create();
        HBaseAdmin admin = new HBaseAdmin(config);
        String table = "t_cdr";
        if (admin.isTableAvailable(table)) {
            admin.disableTable(table);
            admin.deleteTable(table);
        } else {
            HTableDescriptor t = new HTableDescriptor(table.getBytes());
            HColumnDescriptor cf1 = new HColumnDescriptor("cf1".getBytes());
            t.addFamily(cf1);
            admin.createTable(t);
        }*/

        String[] cfs = new String[]{"cf1"};
        boolean isOK = HBaseUtil.createTable(tableName, cfs);
        Assert.assertTrue(isOK);
    }

    @Test
    public void testInsertData() throws Exception {
        /*
        Configuration config = HBaseConfiguration.create();
        HTable table = new HTable(config, "t_cdr");
        String rowKey = "13138654828_" + System.currentTimeMillis();
        Put put = new Put(rowKey.getBytes());
        put.addColumn("cf1".getBytes(), "dest".getBytes(), "886547323".getBytes());
        table.put(put);*/

        String rowKey = "13138654828_1580375759479";
        HBaseUtil.putRow(tableName, rowKey, "cf1", "dest", "886547324");
        HBaseUtil.putRow(tableName, rowKey, "cf1", "type", "2");
        HBaseUtil.putRow(tableName, rowKey, "cf1", "name", "Elson");
    }

    @Test
    public void testQueryData() throws Exception {
/*      Configuration config = HBaseConfiguration.create();

        HTable table = new HTable(config, "t_cdr");
        Get get = new Get("13138654828__".getBytes());
        Result res = table.get(get);
        Cell c1 = res.getColumnLatestCell("cf1".getBytes(), "type".getBytes());
        System.out.println(new String(c1.getValue()));
        Cell c2 = res.getColumnLatestCell("cf1".getBytes(), "dest".getBytes());
        System.out.println(new String(c2.getValue()));
        Cell c3 = res.getColumnLatestCell("cf1".getBytes(), "time".getBytes());
        System.out.println(new String(c3.getValue()));*/


/*       Scan scan = new Scan();
        scan.setStartRow("13138654828_".getBytes());
        scan.setStopRow("13138654828_".getBytes());
        ResultScanner scanner = getScanner..getScanner(scan);
        for (Result r : scanner) {
            System.out.println(new String(r.getColumnLatestCell("cf1".getBytes(), "type".getBytes()).getValue()));
        }
        table.close();*/

        Result result = HBaseUtil.getRow(tableName, "13138654828_1580375759479");
        System.out.println("rowkey == " + Bytes.toString(result.getRow()));
        System.out.println("basic:type == " + Bytes.toString(result.getValue(Bytes.toBytes("cf1"), Bytes.toBytes("type"))));
        System.out.println("basic:dest == " + Bytes.toString(result.getValue(Bytes.toBytes("cf1"), Bytes.toBytes("dest"))));
        System.out.println("basic:time == " + Bytes.toString(result.getValue(Bytes.toBytes("cf1"), Bytes.toBytes("name"))));
        System.out.println("-------------------------------");
        HBaseUtil.getScanner(tableName, "13138654828_1580375759479", "13138654828_1580375759479");
    }

}

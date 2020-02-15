package com.elson.hadoop.phonebillplusprotobuf;

import com.elson.hadoop.phonebillplusprotobuf.protobuf.Phone;
import com.elson.hadoop.phonebillplusprotobuf.utils.HBaseConn;
import org.apache.hadoop.hbase.*;
import org.apache.hadoop.hbase.client.*;
import org.apache.hadoop.hbase.filter.CompareFilter;
import org.apache.hadoop.hbase.filter.FilterList;
import org.apache.hadoop.hbase.filter.PrefixFilter;
import org.apache.hadoop.hbase.filter.SingleColumnValueFilter;
import org.apache.hadoop.hbase.util.Bytes;
import org.junit.Test;

import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class PhoneBillProtoBufTest {

    public static final String tableName = "TN";

    public static final Random r = new Random();

    public static final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyyMMddHHmmss");

    @Test
    public void insert() throws Exception {
        try (Table table = HBaseConn.getTable(tableName)) {
            String rowKey = "13138654828_2016123123123";
            Put put = new Put(rowKey.getBytes());

            put.addColumn("cf1".getBytes(), "type".getBytes(), "1".getBytes());
            put.addColumn("cf1".getBytes(), "time".getBytes(), "2016".getBytes());
            put.addColumn("cf1".getBytes(), "pnum".getBytes(), "13126443655".getBytes());

            table.put(put);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    public void get() throws Exception {

        try (Table table = HBaseConn.getTable(tableName)) {
            String rowKey = "13138654828_2016123123123";
            Get get = new Get(rowKey.getBytes());
            get.addColumn("cf1".getBytes(), "type".getBytes());
            get.addColumn("cf1".getBytes(), "time".getBytes());
            Result result = table.get(get);
            Cell cell = result.getColumnLatestCell("cf1".getBytes(), "type".getBytes());
            System.out.println("========" + new String(CellUtil.cloneValue(cell)));
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    public String getPhoneNum(String prefix) {
        return prefix + String.format("%08d", r.nextInt(99999999));
    }

    public String getDate(String year) {
        return year + String.format("%02d%02d%02d%02d%02d",
                new Object[]{r.nextInt(12) + 1, r.nextInt(29) + 1,
                        r.nextInt(24), r.nextInt(60), r.nextInt(60)});
    }

    public String getDate2(String prefix) {
        return prefix + String.format("%02d%02d%02d",
                new Object[]{r.nextInt(24), r.nextInt(60), r.nextInt(60)});
    }

    @Test
    public void createTable() throws IOException {

        try (HBaseAdmin admin = (HBaseAdmin) HBaseConn.getHBaseConn().getAdmin()) {
            if (admin.tableExists(tableName)) {
                admin.disableTable(tableName);
                admin.deleteTable(tableName);
            }
            HTableDescriptor desc = new HTableDescriptor(TableName.valueOf(tableName));

            HColumnDescriptor family = new HColumnDescriptor("cf1");
            family.setBlockCacheEnabled(true);
            family.setInMemory(true);
            family.setMaxVersions(1);
            desc.addFamily(family);

            admin.createTable(desc);
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    @Test
    public void insertDB() throws Exception {
        List<Put> puts = new ArrayList<>();
        try (Table table = HBaseConn.getTable(tableName)) {
            for (int i = 0; i < 10; i++) {
                String rowKey;
                String phoneNum = getPhoneNum("186");
                for (int j = 0; j < 100; j++) {
                    String phoneDate = getDate("2016");
                    LocalDateTime time = LocalDateTime.parse(phoneDate, formatter);
                    long dataLong = time.toLocalDate().toEpochDay();
                    rowKey = phoneNum + (Long.MAX_VALUE - dataLong);
                    Put put = new Put(rowKey.getBytes());
                    put.addColumn("cf1".getBytes(), "type".getBytes(), (r.nextInt(2) + "").getBytes());
                    put.addColumn("cf1".getBytes(), "time".getBytes(), (phoneDate).getBytes());
                    put.addColumn("cf1".getBytes(), "pnum".getBytes(), getPhoneNum("170").getBytes());
                    puts.add(put);
                    //System.out.println("i=" + i + ";j=" + j);
                }
            }
            table.put(puts);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    public void insertDB2() throws Exception {
        List<Put> puts = new ArrayList<>();
        try (Table table = HBaseConn.getTable(tableName)) {
            for (int i = 0; i < 10; i++) {
                String phoneNum = getPhoneNum("186");
                String rowKey = phoneNum + (Long.MAX_VALUE - Long.parseLong("20161110"));

                Phone.pday.Builder pday = Phone.pday.newBuilder();

                for (int j = 0; j < 100; j++) {
                    String phoneDate = getDate2("20161110");
                    Phone.pdetail.Builder pdetail = Phone.pdetail.newBuilder();

                    pdetail.setPnum(getPhoneNum("170"));
                    pdetail.setTime(phoneDate);
                    pdetail.setType(r.nextInt(2) + "");

                    pday.addPlist(pdetail);
                }

                Put put = new Put(rowKey.getBytes());
                put.addColumn("cf1".getBytes(), "pday".getBytes(), pday.build().toByteArray());
                puts.add(put);
            }
            table.put(puts);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    public void getPhoneData() throws Exception {
        String rowKey = "186935769159223372036834614697";
        try (Table table = HBaseConn.getTable(tableName)) {
            Get get = new Get(rowKey.getBytes());
            get.addColumn("cf1".getBytes(), "pday".getBytes());
            Result result = table.get(get);
            Cell cell = result.getColumnLatestCell("cf1".getBytes(), "pday".getBytes());
            Phone.pday pday = Phone.pday.parseFrom(CellUtil.cloneValue(cell));
            for (Phone.pdetail pdetail : pday.getPlistList()) {
                System.out.println(pdetail.getPnum() + "-" + pdetail.getTime());
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    @Test
    public void scanDB() throws Exception {

        Scan scan = new Scan();
        LocalDateTime startTime = LocalDateTime.parse("20161201000000", formatter);
        long startLong = startTime.toLocalDate().toEpochDay();
        String startRowKey = "18694723872" + (Long.MAX_VALUE - startLong);

        LocalDateTime endTime = LocalDateTime.parse("20160101000000", formatter);
        long stopLong = endTime.toLocalDate().toEpochDay();
        String stopRowKey = "18694723872" + (Long.MAX_VALUE - stopLong);

        scan.withStartRow(startRowKey.getBytes()).withStopRow(stopRowKey.getBytes());

        try (Table table = HBaseConn.getTable(tableName)) {
            ResultScanner resultScanner = table.getScanner(scan);
            for (Result result : resultScanner) {
                System.out.println(new String(CellUtil.cloneValue(result.getColumnLatestCell("cf1".getBytes(), "type".getBytes()))) +
                        new String(CellUtil.cloneValue(result.getColumnLatestCell("cf1".getBytes(), "time".getBytes()))) +
                        new String(CellUtil.cloneValue(result.getColumnLatestCell("cf1".getBytes(), "pnum".getBytes()))));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    @Test
    public void scanDB2() throws Exception {
        FilterList list = new FilterList(FilterList.Operator.MUST_PASS_ALL);
        PrefixFilter prefixFilter = new PrefixFilter("18694723872".getBytes());
        list.addFilter(prefixFilter);

        SingleColumnValueFilter singleColumnValueFilter =
                new SingleColumnValueFilter("cf1".getBytes(), "type".getBytes(), CompareFilter.CompareOp.EQUAL, "0".getBytes());
        list.addFilter(singleColumnValueFilter);

        Scan scan = new Scan();
        scan.setFilter(list);

        try (Table table = HBaseConn.getTable(tableName)) {
            ResultScanner scanner = table.getScanner(scan);
            scanner.forEach(result -> {
                System.out.println("cf1:type == " + Bytes.toString(result.getValue(Bytes.toBytes("cf1"), Bytes.toBytes("type"))));
                System.out.println("cf1:time == " + Bytes.toString(result.getValue(Bytes.toBytes("cf1"), Bytes.toBytes("time"))));
                System.out.println("cf1:pnum == " + Bytes.toString(result.getValue(Bytes.toBytes("cf1"), Bytes.toBytes("pnum"))));
            });
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}

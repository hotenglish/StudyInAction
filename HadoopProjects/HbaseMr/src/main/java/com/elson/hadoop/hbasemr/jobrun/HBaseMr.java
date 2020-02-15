package com.elson.hadoop.hbasemr.jobrun;

import com.elson.hadoop.hbasemr.utils.HBaseConn;
import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.hbase.HColumnDescriptor;
import org.apache.hadoop.hbase.HTableDescriptor;
import org.apache.hadoop.hbase.TableName;
import org.apache.hadoop.hbase.client.*;
import org.apache.hadoop.hbase.io.ImmutableBytesWritable;
import org.apache.hadoop.hbase.mapreduce.TableMapReduceUtil;
import org.apache.hadoop.hbase.mapreduce.TableMapper;
import org.apache.hadoop.hbase.mapreduce.TableReducer;
import org.apache.hadoop.hbase.util.Bytes;
import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Job;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * mapreduce����hbase
 *
 * @author wilson
 */
public class HBaseMr {

    /**
     * ����Ϣ
     */
    public static final String tableName = "word";//����1
    public static final String colf = "content";//����
    public static final String col = "info";//��
    public static final String tableName2 = "stat";//����2

    /**
     * ��ʼ����ṹ����������
     */
    public static void initTB() {
        try (HBaseAdmin admin = (HBaseAdmin) HBaseConn.getHBaseConn().getAdmin()) {
            /*ɾ����*/
            if (admin.tableExists(tableName) || admin.tableExists(tableName2)) {
                System.out.println("table is already exists!");
                admin.disableTable(tableName);
                admin.deleteTable(tableName);
                admin.disableTable(tableName2);
                admin.deleteTable(tableName2);
            }
            /*������*/
            HTableDescriptor desc = new HTableDescriptor(TableName.valueOf(tableName));
            HColumnDescriptor family = new HColumnDescriptor(colf);
            desc.addFamily(family);
            admin.createTable(desc);
            HTableDescriptor desc2 = new HTableDescriptor(TableName.valueOf(tableName2));
            HColumnDescriptor family2 = new HColumnDescriptor(colf);
            desc2.addFamily(family2);
            admin.createTable(desc2);

        } catch (Exception e) {
            e.printStackTrace();
        }

        /*��������*/
        List<Put> lp = new ArrayList<>();
        try (Table table = HBaseConn.getTable(tableName)) {
            Put p1 = new Put(Bytes.toBytes("1"));
            p1.addColumn(colf.getBytes(), col.getBytes(), ("The Apache Hadoop software library is a framework").getBytes());
            lp.add(p1);
            Put p2 = new Put(Bytes.toBytes("2"));
            p2.addColumn(colf.getBytes(), col.getBytes(), ("The common utilities that support the other Hadoop modules").getBytes());
            lp.add(p2);
            Put p3 = new Put(Bytes.toBytes("3"));
            p3.addColumn(colf.getBytes(), col.getBytes(), ("Hadoop by reading the documentation").getBytes());
            lp.add(p3);
            Put p4 = new Put(Bytes.toBytes("4"));
            p4.addColumn(colf.getBytes(), col.getBytes(), ("Hadoop from the release page").getBytes());
            lp.add(p4);
            Put p5 = new Put(Bytes.toBytes("5"));
            p5.addColumn(colf.getBytes(), col.getBytes(), ("Hadoop on the mailing list").getBytes());
            lp.add(p5);
            table.put(lp);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    /**
     * MyMapper �̳� TableMapper
     * TableMapper<Text,IntWritable>
     * Text:�����key���ͣ�
     * IntWritable�������value����
     */
    public static class MyMapper extends TableMapper<Text, IntWritable> {
        private static IntWritable one = new IntWritable(1);
        private static Text word = new Text();

        @Override
        //���������Ϊ��key��rowKey�� value��һ�����ݵĽ����Result
        protected void map(ImmutableBytesWritable key, Result value,
                           Context context) throws IOException, InterruptedException {
            //��ȡһ�������е�colf��col
            String words = Bytes.toString(value.getValue(Bytes.toBytes(colf), Bytes.toBytes(col)));// ������ֻ��һ�����壬�����Ҿ�ֱ�ӻ�ȡÿһ�е�ֵ
            //���ո�ָ�
            String itr[] = words.toString().split(" ");
            //ѭ�����word��1
            for (int i = 0; i < itr.length; i++) {
                word.set(itr[i]);
                context.write(word, one);
            }
        }
    }

    /**
     * MyReducer �̳� TableReducer
     * TableReducer<Text,IntWritable>
     * Text:�����key���ͣ�
     * IntWritable�������value���ͣ�
     * ImmutableBytesWritable��������ͣ���ʾrowkey������
     */
    public static class MyReducer extends
            TableReducer<Text, IntWritable, ImmutableBytesWritable> {
        @Override
        protected void reduce(Text key, Iterable<IntWritable> values,
                              Context context) throws IOException, InterruptedException {
            //��mapper���������
            int sum = 0;
            for (IntWritable val : values) {//����
                sum += val.get();
            }
            // ����put������rowkeyΪ����
            Put put = new Put(Bytes.toBytes(key.toString()));
            // ��װ����
            put.add(Bytes.toBytes(colf), Bytes.toBytes(col), Bytes.toBytes(String.valueOf(sum)));
            //д��hbase,��Ҫָ��rowkey��put
            context.write(new ImmutableBytesWritable(Bytes.toBytes(key.toString())), put);
        }

    }

    public static void main(String[] args) throws IOException,
            ClassNotFoundException, InterruptedException {

        Configuration config = HBaseConn.getHBaseConn().getConfiguration();
        //��ʼ����
        initTB();//��ʼ����
        //����job
        Job job = Job.getInstance(config, "HBaseMr");//job
        job.setJarByClass(HBaseMr.class);//����
        //����scan
        Scan scan = new Scan();
        //����ָ����ѯĳһ��
        scan.addColumn(Bytes.toBytes(colf), Bytes.toBytes(col));
        //������ѯhbase��mapper�����ñ�����scan��mapper�ࡢmapper�����key��mapper�����value
        TableMapReduceUtil.initTableMapperJob(tableName, scan, MyMapper.class, Text.class, IntWritable.class, job);
        //����д��hbase��reducer��ָ��������reducer�ࡢjob
        TableMapReduceUtil.initTableReducerJob(tableName2, MyReducer.class, job);
        System.exit(job.waitForCompletion(true) ? 0 : 1);
    }

}
package com.elson.hadoop.hbasemr.reduce;

import org.apache.hadoop.hbase.client.Put;
import org.apache.hadoop.hbase.io.ImmutableBytesWritable;
import org.apache.hadoop.hbase.mapreduce.TableReducer;
import org.apache.hadoop.hbase.util.Bytes;
import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.Text;

import java.io.IOException;

public class WCTableReducer extends TableReducer<Text, IntWritable, ImmutableBytesWritable> {

    public static final byte[] CF = "cf".getBytes();

    public static final byte[] count = "count".getBytes();

    protected void reduce(Text key, Iterable<IntWritable> iterable, Context context)
            throws IOException, InterruptedException {
        int sum = 0;
        for (IntWritable i : iterable) {
            sum = sum + i.get();
        }
        Put put = new Put(key.getBytes());
        put.addColumn(CF, count, Bytes.toBytes(sum));
        context.write(null, put);
    }

}

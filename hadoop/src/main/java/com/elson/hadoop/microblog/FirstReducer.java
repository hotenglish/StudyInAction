package com.elson.hadoop.microblog;

import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Reducer;

import java.io.IOException;

/**
 * Í³¼Ætf, n
 * @author root
 *
 */
public class FirstReducer extends Reducer<Text, IntWritable, Text, IntWritable> {

    protected void reduce(Text key, Iterable<IntWritable> iterable, Context context)
            throws IOException, InterruptedException {
        int sum = 0;
        for (IntWritable i : iterable) {
            sum = sum + i.get();
        }
        if (key.equals(new Text("count"))) {
            System.out.println(key.toString() + "______________________" + sum);
        }
        context.write(key, new IntWritable(sum));
    }

}

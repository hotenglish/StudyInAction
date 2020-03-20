package com.elson.hadoop.microblog;

import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Reducer;

import java.io.IOException;

/**
 * 第二JOB的结果：
 * 九阳 2223
 * 豆浆 2345
 *
 * @author root
 */
public class SecondReducer extends Reducer<Text, IntWritable, Text, IntWritable> {

    protected void reduce(Text key, Iterable<IntWritable> iterable, Context context)
            throws IOException, InterruptedException {
        int sum = 0;
        for (IntWritable intWritable : iterable) {
            sum += intWritable.get();
        }
        context.write(new Text(key), new IntWritable(sum));
    }

}

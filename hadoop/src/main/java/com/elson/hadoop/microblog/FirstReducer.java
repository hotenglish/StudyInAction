package com.elson.hadoop.microblog;

import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Reducer;

import java.io.IOException;

/**
 * 统计tf, n
 * TF统计在一个文件中
 * 第一个JOB的结果：
 * 九阳-001 2
 * 九阳-002 1
 * N统计在另一个文件中：
 * count 192234
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

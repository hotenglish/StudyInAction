package com.elson.hadoop.mapper;

import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Mapper;

import java.io.IOException;
import java.util.StringTokenizer;

public class WcMapper extends Mapper<LongWritable, Text, Text, IntWritable> {

    protected void map(LongWritable key, Text value, Context context)
            throws IOException, InterruptedException {
        String line = value.toString();
        StringTokenizer st = new StringTokenizer(line);
        while (st.hasMoreTokens()) {
            String word = st.nextToken();
            context.write(new Text(word), new IntWritable(1)); // map output
        }
    }

}

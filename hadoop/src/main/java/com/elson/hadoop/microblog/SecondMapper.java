package com.elson.hadoop.microblog;

import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Mapper;
import org.apache.hadoop.mapreduce.lib.input.FileSplit;

import java.io.IOException;

public class SecondMapper extends Mapper<LongWritable, Text, Text, IntWritable> {

    @Override
    protected void map(LongWritable key, Text value, Context context)
            throws IOException, InterruptedException {

        FileSplit inputSplit = (FileSplit) context.getInputSplit();

        if (!inputSplit.getPath().getName().contains("part-r-00003")) {

            String[] values = value.toString().trim().split("\t");

            if (values.length >= 2) {
                String[] split = values[0].trim().split("_");
                if (split.length >= 2) {
                    String id = split[0];
                    context.write(new Text(id), new IntWritable(1));
                }
            }
        } else {
            System.out.println(value.toString() + "----");
        }

    }

}

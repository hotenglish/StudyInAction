package com.elson.hadoop.mapper;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Mapper;

import java.io.IOException;

public class RecommendFriendMapper extends Mapper<LongWritable, Text, Text, Text> {

    private static final Log LOGGER = LogFactory.getLog(RecommendFriendMapper.class);

    protected void map(LongWritable key, Text value, Context context)
            throws IOException, InterruptedException {
        String line = value.toString();
        String[] ss = line.split("=");
        LOGGER.info("ss->" + ss);
        System.out.println("ss->" + ss);
        context.write(new Text(ss[0]), new Text(ss[1]));
        context.write(new Text(ss[1]), new Text(ss[0]));
    }

}

package com.elson.hadoop.jobrun;

import com.elson.hadoop.mapper.Demo090201Mapper;
import com.elson.hadoop.reduce.Demo090201Reducer;
import com.elson.hadoop.utils.RecordWritable;
import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.NullWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Job;
import org.apache.hadoop.mapreduce.lib.input.FileInputFormat;
import org.apache.hadoop.mapreduce.lib.output.FileOutputFormat;

import java.io.IOException;

public class Demo090204 {

    final static String INPUT_PATH = "/usr/input/site/";

    final static String OUT_PATH = "/usr/output/";

    public static void main(String[] args) throws IOException, ClassNotFoundException, InterruptedException {

        Configuration configuration = new Configuration();

        Job job = Job.getInstance(configuration);
        job.setJarByClass(Demo090204.class);
        job.setMapperClass(Demo090201Mapper.class);
        job.setReducerClass(Demo090201Reducer.class);

        job.setMapOutputKeyClass(Text.class);
        job.setMapOutputValueClass(Text.class);
        job.setOutputKeyClass(RecordWritable.class);
        job.setOutputValueClass(NullWritable.class);

        FileInputFormat.setInputPaths(job, new Path(INPUT_PATH));
        FileOutputFormat.setOutputPath(job, new Path(OUT_PATH));
        job.waitForCompletion(true);

    }

}

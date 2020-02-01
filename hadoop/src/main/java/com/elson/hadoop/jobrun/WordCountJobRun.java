package com.elson.hadoop.jobrun;

import com.elson.hadoop.mapper.WcMapper;
import com.elson.hadoop.reduce.WcReducer;
import com.elson.hadoop.utils.FileUtil;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.mapreduce.Job;
import org.apache.hadoop.mapreduce.lib.input.FileInputFormat;
import org.apache.hadoop.mapreduce.lib.output.FileOutputFormat;

public class WordCountJobRun {

    public static void main(String[] args) {
        FileUtil.deleteDir("output");
        Configuration conf = new Configuration();
        String[] otherArgs = new String[]{"/usr/input/wc/", "/output/"};
        if (otherArgs.length != 2) {
            System.err.println("Usage:Merge and duplicate removal <in> <out>");
            System.exit(2);
        }
        try {
            Job job = Job.getInstance(conf);
            job.setJarByClass(WordCountJobRun.class);
            job.setMapperClass(WcMapper.class);
            job.setReducerClass(WcReducer.class);
            job.setMapOutputKeyClass(Text.class);
            job.setMapOutputValueClass(IntWritable.class);

            FileInputFormat.addInputPath(job, new Path(otherArgs[0]));
            FileOutputFormat.setOutputPath(job, new Path(otherArgs[1]));
            System.exit(job.waitForCompletion(true) ? 0 : 1);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}

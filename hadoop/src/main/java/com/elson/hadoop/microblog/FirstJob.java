package com.elson.hadoop.microblog;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Job;
import org.apache.hadoop.mapreduce.lib.input.FileInputFormat;
import org.apache.hadoop.mapreduce.lib.output.FileOutputFormat;
import org.apache.hadoop.io.IntWritable;

public class FirstJob {

    public static void main(String[] args) {
        Configuration conf = new Configuration();
        String[] otherArgs = new String[]{"/usr/input/microblog/", "/output/weibo1/"};
        if (otherArgs.length != 2) {
            System.err.println("Usage:Merge and duplicate removal <in> <out>");
            System.exit(2);
        }
        try {
            Job job = Job.getInstance(conf);
            job.setJarByClass(FirstJob.class);
            job.setJobName("Weibo1");

            job.setMapOutputKeyClass(Text.class);
            job.setMapOutputValueClass(IntWritable.class);

            job.setNumReduceTasks(4);
            job.setPartitionerClass(FirstPartition.class);
            job.setMapperClass(FirstMapper.class);
            job.setCombinerClass(FirstReducer.class);
            job.setReducerClass(FirstReducer.class);

            FileInputFormat.addInputPath(job, new Path(otherArgs[0]));
            FileOutputFormat.setOutputPath(job, new Path(otherArgs[1]));

            boolean f = job.waitForCompletion(true);
            if (f) {
                System.out.println("job finished");
            } else {
                System.out.println("job do not finish");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}

package com.elson.hadoop.microblog;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.FileSystem;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Job;
import org.apache.hadoop.mapreduce.lib.input.FileInputFormat;
import org.apache.hadoop.mapreduce.lib.output.FileOutputFormat;

public class SecondJob {

    public static void main(String[] args) {
        Configuration conf = new Configuration();
        String[] otherArgs = new String[]{"/output/weibo1/", "/output/weibo2"};
        if (otherArgs.length != 2) {
            System.err.println("Usage:Merge and duplicate removal <in> <out>");
            System.exit(2);
        }
        try {
            Job job = Job.getInstance(conf);
            job.setJarByClass(SecondJob.class);
            job.setJobName("Weibo2");

            job.setMapOutputKeyClass(Text.class);
            job.setMapOutputValueClass(IntWritable.class);

            job.setMapperClass(SecondMapper.class);
            job.setCombinerClass(SecondReducer.class);
            job.setReducerClass(SecondReducer.class);

            FileInputFormat.addInputPath(job, new Path(otherArgs[0]));

            FileSystem fileSystem = FileSystem.get(conf);
            Path outPath = new Path(otherArgs[1]);
            if (fileSystem.exists(outPath)) {
                fileSystem.delete(outPath, true);
            }
            FileOutputFormat.setOutputPath(job, outPath);

            boolean f = job.waitForCompletion(true);
            if (f) {
                System.out.println("job2 success");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}

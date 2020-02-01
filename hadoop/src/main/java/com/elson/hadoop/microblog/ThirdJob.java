package com.elson.hadoop.microblog;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.FileSystem;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Job;
import org.apache.hadoop.mapreduce.lib.input.FileInputFormat;
import org.apache.hadoop.mapreduce.lib.output.FileOutputFormat;

public class ThirdJob {

    public static void main(String[] args) {
        Configuration conf = new Configuration();
        String[] otherArgs = new String[]{"/output/weibo1/part-r-00003", "/output/weibo2/part-r-00000", "/output/weibo1/", "/output/weibo3/"};
        try {
            Job job = Job.getInstance(conf);
            job.setJarByClass(ThirdJob.class);
            job.setJobName("Weibo3");

            job.addCacheFile(new Path(otherArgs[0]).toUri());
            job.addCacheFile(new Path(otherArgs[1]).toUri());

            job.setMapperClass(ThirdMapper.class);
            job.setReducerClass(ThirdReducer.class);

            job.setMapOutputKeyClass(Text.class);
            job.setMapOutputValueClass(Text.class);

            FileInputFormat.addInputPath(job, new Path(otherArgs[2]));

            FileSystem fileSystem = FileSystem.get(conf);
            Path outPath = new Path(otherArgs[3]);
            if (fileSystem.exists(outPath)) {
                fileSystem.delete(outPath, true);
            }
            FileOutputFormat.setOutputPath(job, outPath);

            boolean f = job.waitForCompletion(true);
            if (f) {
                System.out.println("job3 success");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}

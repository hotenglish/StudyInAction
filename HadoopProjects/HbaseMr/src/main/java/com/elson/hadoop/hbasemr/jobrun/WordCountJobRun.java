package com.elson.hadoop.hbasemr.jobrun;

import com.elson.hadoop.hbasemr.mapper.WcMapper;
import com.elson.hadoop.hbasemr.reduce.WCTableReducer;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.mapreduce.lib.input.FileInputFormat;
import org.apache.hadoop.mapreduce.Job;
import org.apache.hadoop.hbase.mapreduce.TableMapReduceUtil;

public class WordCountJobRun {

    public static void main(String[] args) {

        Configuration conf = new Configuration();

        String[] otherArgs = new String[]{"/usr/input/wc/"};

        try {
            Job job = Job.getInstance(conf);
            job.setJarByClass(WordCountJobRun.class);
            job.setMapperClass(WcMapper.class);
            job.setMapOutputKeyClass(Text.class);
            job.setMapOutputValueClass(IntWritable.class);

            FileInputFormat.addInputPath(job, new Path(otherArgs[0]));

            String targetTable = "wc";
            TableMapReduceUtil.initTableReducerJob(targetTable, WCTableReducer.class, job);

            System.exit(job.waitForCompletion(true) ? 0 : 1);

        } catch (Exception e) {
            e.printStackTrace();
        }

    }

}

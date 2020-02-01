package com.elson.hadoop.jobrun;

import com.elson.hadoop.selfdefinedgroup.GroupHot;
import com.elson.hadoop.selfdefinedpartition.FirstPartition;
import com.elson.hadoop.selfdefinedsort.SortHot;
import com.elson.hadoop.selfdefinekey.KeyPair;
import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Job;
import org.apache.hadoop.mapreduce.Reducer;
import org.apache.hadoop.mapreduce.Mapper;
import org.apache.hadoop.mapreduce.lib.input.FileInputFormat;
import org.apache.hadoop.mapreduce.lib.output.FileOutputFormat;

import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoField;

public class WeatherRunJob {

    static class HotMapper extends Mapper<LongWritable, Text, KeyPair, Text> {

        protected void map(LongWritable key, Text value, Context context)
                throws IOException, InterruptedException {
            String line = value.toString();
            String[] ss = line.split("=");
            if (ss.length == 2) {
                try {
                    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
                    LocalDateTime time = LocalDateTime.parse(ss[0], formatter);
                    int year = time.get(ChronoField.YEAR);
                    String hot = ss[1].substring(0, ss[1].indexOf("?C"));
                    KeyPair keyPair = new KeyPair();
                    keyPair.setYear(year);
                    keyPair.setHot(Integer.parseInt(hot));
                    context.write(keyPair, value);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }
    }

    static class HotReduce extends Reducer<KeyPair, Text, KeyPair, Text> {

        protected void reduce(KeyPair kp, Iterable<Text> value, Context context)
                throws IOException, InterruptedException {
            for (Text v : value) {
                context.write(kp, v);
            }
        }
    }

    public static void main(String[] args) {
        Configuration conf = new Configuration();
        String[] otherArgs = new String[]{"/usr/input/weather/", "/output/"};
        if (otherArgs.length != 2) {
            System.err.println("Usage:Merge and duplicate removal <in> <out>");
            System.exit(2);
        }
        try {
            Job job = Job.getInstance(conf);
            job.setJarByClass(WeatherRunJob.class);
            job.setMapperClass(HotMapper.class);
            job.setReducerClass(HotReduce.class);
            job.setMapOutputKeyClass(KeyPair.class);
            job.setMapOutputValueClass(Text.class);

            job.setNumReduceTasks(3);
            job.setPartitionerClass(FirstPartition.class);
            job.setSortComparatorClass(SortHot.class);
            job.setGroupingComparatorClass(GroupHot.class);

            FileInputFormat.addInputPath(job, new Path(otherArgs[0]));
            FileOutputFormat.setOutputPath(job, new Path(otherArgs[1]));
            System.exit(job.waitForCompletion(true) ? 0 : 1);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}

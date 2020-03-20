package com.elson.hadoop.microblog;

import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Mapper;
import org.wltea.analyzer.core.IKSegmenter;
import org.wltea.analyzer.core.Lexeme;

import java.io.IOException;
import java.io.StringReader;

/**
 * ÿ���ʣ��ڸ���΢���г��ֵĴ���TF��ͳ��(΢��������)
 */
public class FirstMapper extends Mapper<LongWritable, Text, Text, IntWritable> {

    protected void map(LongWritable key, Text value, Context context)
            throws IOException, InterruptedException {
        String[] v = value.toString().trim().split("\t");
        if (v.length >= 2) {
            String id = v[0].trim();
            String content = v[1].trim();

            StringReader sr = new StringReader(content);
            IKSegmenter ikSegmenter = new IKSegmenter(sr, true);
            Lexeme word = null;
            while ((word = ikSegmenter.next()) != null) {
                String w = word.getLexemeText();
                // ͳ��TF
                context.write(new Text(w + "_" + id), new IntWritable(1));
            }
            // ͳ��N
            context.write(new Text("count"), new IntWritable(1));
        } else {
            System.out.println(value.toString() + "---------------------");
        }
    }

}

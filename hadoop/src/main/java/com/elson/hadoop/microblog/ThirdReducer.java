package com.elson.hadoop.microblog;

import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Reducer;

import java.io.IOException;

public class ThirdReducer extends Reducer<Text, Text, Text, Text> {

    @Override
    protected void reduce(Text key, Iterable<Text> iterable, Context context)
            throws IOException, InterruptedException {

        StringBuffer sb = new StringBuffer();
        for (Text text : iterable) {
            sb.append(text.toString() + "\t");
        }

        context.write(key, new Text(sb.toString()));

    }

}

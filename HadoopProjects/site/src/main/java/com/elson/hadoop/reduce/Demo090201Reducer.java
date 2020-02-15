package com.elson.hadoop.reduce;

import com.elson.hadoop.utils.RecordWritable;
import org.apache.hadoop.io.NullWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Reducer;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

public class Demo090201Reducer extends Reducer<Text, Text, RecordWritable, NullWritable> {

    @Override
    protected void reduce(Text key, Iterable<Text> v2s, Context context)
            throws IOException, InterruptedException {

        ArrayList<RecordWritable> list = new ArrayList<>();

        for (Text text : v2s) {
            String[] splited = text.toString().split("=");

            RecordWritable record = new RecordWritable();
            record.setProduct_no(splited[0]);
            record.setLac_id(splited[1]);
            record.setMoment(Integer.parseInt(splited[2]));
            record.setStart_time(splited[3]);
            record.setUser_id(splited[4]);
            record.setCounty_id(splited[5]);
            record.setStaytime(Integer.parseInt(splited[6]));
            record.setCity_id(splited[7]);

            list.add(record);

            System.out.println(record.toString());
        }

        System.out.println("--------------------------------------");

        //对List中数据进行排序(自定义比较器)
        Collections.sort(list, new Comparator<RecordWritable>() {
            @Override
            public int compare(RecordWritable r1, RecordWritable r2) {

                //调用RecordWritable的compareTo()方法
                return (r1.compareTo(r2));
            }
        });

        for (int i = 0; i < list.size(); i++) {

            //滤过最后一条记录
            if (i != list.size() - 1) {

                //取出相邻的两个RecordWritable
                RecordWritable record_pre = list.get(i);
                RecordWritable record_next = list.get(i + 1);

                if (record_pre.getLac_id().equals(record_next.getLac_id())) {

                    //将相加后的staytime赋予后一条记录
                    record_next.setStaytime(record_next.getStaytime() + record_pre.getStaytime());

                    //移除前一条记录
                    list.remove(record_pre);
                }
            }
        }

        for (RecordWritable record : list) {
            context.write(record, NullWritable.get());
        }
    }
}
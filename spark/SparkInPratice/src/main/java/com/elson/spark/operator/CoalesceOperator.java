package com.elson.spark.operator;

import org.apache.spark.SparkConf;
import org.apache.spark.api.java.JavaRDD;
import org.apache.spark.api.java.JavaSparkContext;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;

public class CoalesceOperator {

    public static void main(String args[]) {
        SparkConf conf = new SparkConf().setMaster("local").setAppName("CoalesceOperator");
        conf.set("spark.default.parellelism", "3");
        JavaSparkContext sc = new JavaSparkContext(conf);
        List<String> names = Arrays.asList("xurunyun1", "xurunyun2", "xurunyun3",
                "xurunyun4", "xurunyun5", "xurunyun6",
                "xurunyun7", "xurunyun8", "xurunyun9",
                "xurunyun10", "xurunyun11", "xurunyun12");
        JavaRDD<String> staffRDD = sc.parallelize(names, 6);
        JavaRDD<String> staffRDD2 = staffRDD.mapPartitionsWithIndex((Integer index, Iterator<String> iterator) -> {
            List<String> list = new ArrayList<>();
            while (iterator.hasNext()) {
                String staff = iterator.next();
                list.add("Department[" + (index + 1) + "]" + staff);
            }
            return list.iterator();
        }, true);

        staffRDD2.foreach(e -> System.out.println(e));

        JavaRDD<String> staffRDD3 = staffRDD2.coalesce(3);
        JavaRDD<String> staffRDD4 = staffRDD3.mapPartitionsWithIndex((Integer index, Iterator<String> iterator) -> {
            List<String> list = new ArrayList<>();
            while (iterator.hasNext()) {
                String staff = iterator.next();
                list.add("Department[" + (index + 1) + "]" + staff);
            }
            return list.iterator();
        }, true);

        staffRDD4.foreach(e -> System.out.println(e));
        sc.close();
    }

}

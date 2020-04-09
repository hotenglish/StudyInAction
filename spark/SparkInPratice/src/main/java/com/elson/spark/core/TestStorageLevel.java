package com.elson.spark.core;

import org.apache.spark.SparkConf;
import org.apache.spark.api.java.JavaRDD;
import org.apache.spark.api.java.JavaSparkContext;

public class TestStorageLevel {

    public static void main(String args[]) {
        SparkConf conf = new SparkConf().setMaster("local").setAppName("TestStorageLevel");
        JavaSparkContext sc = new JavaSparkContext(conf);

        JavaRDD<String> text = sc.textFile("src/main/resources/top.txt");

        text = text.cache();

        //281
        //cost:1953
        //281
        //cost:239
        //281
        //cost:142

        //281
        //cost:2166
        //281
        //cost:194
        //281
        //cost:120

        long startTime1 = System.currentTimeMillis();
        long count1 = text.count();
        System.out.println(count1);
        long endTime1 = System.currentTimeMillis();
        System.out.println("cost:" + (endTime1 - startTime1));

        long startTime2 = System.currentTimeMillis();
        long count2 = text.count();
        System.out.println(count2);
        long endTime2 = System.currentTimeMillis();
        System.out.println("cost:" + (endTime2 - startTime2));

        long startTime3 = System.currentTimeMillis();
        long count3 = text.count();
        System.out.println(count3);
        long endTime3 = System.currentTimeMillis();
        System.out.println("cost:" + (endTime3 - startTime3));

        sc.close();
    }
}

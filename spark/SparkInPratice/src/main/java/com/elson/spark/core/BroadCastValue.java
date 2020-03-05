package com.elson.spark.core;

import org.apache.spark.SparkConf;
import org.apache.spark.api.java.JavaRDD;
import org.apache.spark.api.java.JavaSparkContext;
import org.apache.spark.broadcast.Broadcast;

import java.util.Arrays;
import java.util.List;

public class BroadCastValue {

    public static void main(String args[]) {

        SparkConf conf = new SparkConf().setMaster("local").setAppName("BroadCastValue");

        JavaSparkContext sc = new JavaSparkContext(conf);

        final int f = 3;

        final Broadcast<Integer> broadCastFactor = sc.broadcast(f);

        List<Integer> numberList = Arrays.asList(1, 2, 3, 4, 5);

        JavaRDD<Integer> listRDD = sc.parallelize(numberList);

        JavaRDD<Integer> results = listRDD.map(num -> num * broadCastFactor.getValue());

        results.foreach(result -> System.out.println(result));

        sc.close();

    }
}

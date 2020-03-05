package com.elson.spark.core;

import org.apache.spark.Accumulator;
import org.apache.spark.SparkConf;
import org.apache.spark.api.java.JavaRDD;
import org.apache.spark.api.java.JavaSparkContext;

import java.util.Arrays;
import java.util.List;

public class AccumulatorValue {

    public static void main(String args[]) throws InterruptedException {

        SparkConf conf = new SparkConf().setMaster("local").setAppName("AccumulatorValue");

        JavaSparkContext sc = new JavaSparkContext(conf);

        final Accumulator<Integer> sum = sc.accumulator(0, "our Accumulator");

        List<Integer> numberList = Arrays.asList(1, 2, 3, 4, 5);

        JavaRDD<Integer> listRDD = sc.parallelize(numberList);

        listRDD =listRDD.cache();

        listRDD.foreach(num -> {
            sum.add(num);
            // if uncomment this line, it will cause ' Can't read accumulator value in task' exception
            // System.out.println(sum.value());
        });

        System.out.println(sum.value());

        Thread.sleep(60 * 1000 * 1000);

        sc.close();

    }

}

package com.elson.spark.core;

import org.apache.spark.SparkConf;
import org.apache.spark.api.java.JavaRDD;
import org.apache.spark.api.java.JavaSparkContext;
import org.apache.spark.util.AccumulatorV2;
import org.apache.spark.util.LongAccumulator;

import java.util.Arrays;
import java.util.List;

public class AccumulatorValue {

    public static void main(String args[]) throws InterruptedException {

        SparkConf conf = new SparkConf().setMaster("local").setAppName("AccumulatorValue");

        JavaSparkContext sc = new JavaSparkContext(conf);

        final AccumulatorV2 sum = new LongAccumulator();

        sc.sc().register(sum,"our Accumulator");

        List<Long> numberList = Arrays.asList(1L, 2L, 3L, 4L, 5L);

        JavaRDD<Long> listRDD = sc.parallelize(numberList);

        listRDD =listRDD.cache();

        listRDD.foreach(num -> {
            sum.add(num);
            // if uncomment this line, it will cause ' Can't read accumulator value in task' exception
            // System.out.println(sum.value());
        });

        System.out.println(sum.value());

        Thread.sleep(60 * 1000 * 1000);

        sc.close();

        System.exit(-1);

    }

}

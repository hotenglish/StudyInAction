package com.elson.spark.operator;

import org.apache.spark.SparkConf;
import org.apache.spark.api.java.JavaRDD;
import org.apache.spark.api.java.JavaSparkContext;

import java.util.Arrays;
import java.util.List;

public class TakeOperator {

    public static void main(String args[]) {

        SparkConf conf = new SparkConf().setMaster("local").setAppName("TakeOperator");

        JavaSparkContext sc = new JavaSparkContext(conf);

        List<Integer> numbersList = Arrays.asList(1, 2, 3, 4, 5);

        JavaRDD<Integer> nubmers = sc.parallelize(numbersList);

        List<Integer> top3Number = nubmers.take(3);

        top3Number.forEach(e -> System.out.println(e));

        sc.close();

    }
}

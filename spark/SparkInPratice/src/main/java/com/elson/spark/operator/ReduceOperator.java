package com.elson.spark.operator;

import org.apache.spark.SparkConf;
import org.apache.spark.api.java.JavaRDD;
import org.apache.spark.api.java.JavaSparkContext;

import java.util.Arrays;
import java.util.List;

public class ReduceOperator {

    public static void main(String args[]) {

        SparkConf config = new SparkConf()
                .setMaster("local")
                .setAppName("ReduceOperator");

        JavaSparkContext sc = new JavaSparkContext(config);

        List<Integer> numberList = Arrays.asList(1, 2, 3, 4, 5);

        JavaRDD<Integer> numbers = sc.parallelize(numberList);

        int sum = numbers.reduce((Integer a, Integer b) -> a + b);

        System.out.println(sum);

        sc.close();

    }

}

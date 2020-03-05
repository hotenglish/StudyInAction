package com.elson.spark.operator;

import org.apache.spark.SparkConf;
import org.apache.spark.api.java.JavaRDD;
import org.apache.spark.api.java.JavaSparkContext;

import java.util.Arrays;
import java.util.List;

public class MapOperator {

    public static void main(String args[]) {
        SparkConf conf = new SparkConf().setAppName("MapOperator")
                .setMaster("local");
        JavaSparkContext sc = new JavaSparkContext(conf);
        List<Integer> numbers = Arrays.asList(1, 2, 3, 4, 5);
        JavaRDD<Integer> numberRDD = sc.parallelize(numbers);
        // input Function[T, R]
        JavaRDD<Integer> results = numberRDD.map(number -> number * 10);
        // input VoidFunction[T]
        results.foreach(result -> System.out.println(result));
        sc.close();
    }

}

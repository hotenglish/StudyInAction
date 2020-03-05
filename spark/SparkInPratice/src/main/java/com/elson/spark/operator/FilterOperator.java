package com.elson.spark.operator;

import org.apache.spark.SparkConf;
import org.apache.spark.api.java.JavaRDD;
import org.apache.spark.api.java.JavaSparkContext;

import java.util.Arrays;
import java.util.List;

public class FilterOperator {

    public static void main(String args[]) {
        SparkConf conf = new SparkConf().setMaster("local").setAppName("FilterOperator");
        JavaSparkContext sc = new JavaSparkContext(conf);
        List<Integer> numbers = Arrays.asList(1, 2, 3, 4, 5);
        JavaRDD<Integer> numberRDD = sc.parallelize(numbers).filter(number -> number % 2 == 0);
        numberRDD.foreach(number -> System.out.println(number));
        sc.close();
    }

}

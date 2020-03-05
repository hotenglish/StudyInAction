package com.elson.spark.operator;

import org.apache.spark.SparkConf;
import org.apache.spark.api.java.JavaRDD;
import org.apache.spark.api.java.JavaSparkContext;

import java.util.Arrays;
import java.util.List;

public class CollectOperator {

    public static void main(String args[]) {

        SparkConf conf = new SparkConf().setMaster("local").setAppName("CollectOperator");

        JavaSparkContext sc = new JavaSparkContext(conf);

        List<Integer> numberList = Arrays.asList(1, 2, 3, 4, 5);

        JavaRDD<Integer> numbers = sc.parallelize(numberList);

        JavaRDD<Integer> doubleNumbers = numbers.map(number -> number * 2);

        List<Integer> doubleNumberList = doubleNumbers.collect();

        for (Integer num : doubleNumberList) {
            System.out.println(num);
        }

        sc.close();
    }

}

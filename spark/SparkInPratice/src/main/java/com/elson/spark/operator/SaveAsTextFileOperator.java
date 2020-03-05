package com.elson.spark.operator;

import org.apache.spark.SparkConf;
import org.apache.spark.api.java.JavaRDD;
import org.apache.spark.api.java.JavaSparkContext;

import java.util.Arrays;
import java.util.List;

public class SaveAsTextFileOperator {

    public static void main(String args[]) {

        SparkConf conf = new SparkConf().setAppName("SaveAsTextFileOperator")
                .setMaster("local");

        JavaSparkContext sc = new JavaSparkContext(conf);

        List<Integer> numbers = Arrays.asList(1, 2, 3, 4, 5);

        JavaRDD<Integer> numberRDD = sc.parallelize(numbers);

        // input Function[T, R]
        JavaRDD<Integer> results = numberRDD.map(number -> number * 2);

        // input VoidFunction[T]
        results.saveAsTextFile("./SaveAsTextFileOperator");

        sc.close();
    }

}

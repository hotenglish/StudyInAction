package com.elson.spark.operator;

import org.apache.spark.SparkConf;
import org.apache.spark.api.java.JavaRDD;
import org.apache.spark.api.java.JavaSparkContext;

import java.util.Arrays;
import java.util.List;

public class TakeSampleOperator {

    public static void main(String args[]) {

        SparkConf conf = new SparkConf().setMaster("local").setAppName("TakeSampleOperator");

        JavaSparkContext sc = new JavaSparkContext(conf);

        List<String> numbersList = Arrays.asList("xurunyun", "liangyongqi", "wangfei", "yasaka");

        JavaRDD<String> nameRDD = sc.parallelize(numbersList, 1);

        List<String> list = nameRDD.takeSample(false, 2, 200L);

        list.forEach(e -> System.out.println(e));

        sc.close();

    }

}

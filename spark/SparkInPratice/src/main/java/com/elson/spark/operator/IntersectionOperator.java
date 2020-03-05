package com.elson.spark.operator;

import org.apache.spark.SparkConf;
import org.apache.spark.api.java.JavaRDD;
import org.apache.spark.api.java.JavaSparkContext;

import java.util.Arrays;
import java.util.List;

public class IntersectionOperator {

    public static void main(String args[]) {

        SparkConf conf = new SparkConf().setMaster("local").setAppName("IntersectionOperator");

        JavaSparkContext sc = new JavaSparkContext(conf);

        List<String> namesList1 = Arrays.asList("xurunyun", "liangyongqi", "wangfei", "yasaka");

        List<String> namesList2 = Arrays.asList("xurunyun", "liangyongqi2", "wangfei3", "yasaka4");

        JavaRDD<String> nameRDD1 = sc.parallelize(namesList1, 2);

        JavaRDD<String> nameRDD2 = sc.parallelize(namesList2, 2);

        nameRDD1.intersection(nameRDD2).foreach(e -> System.out.println(e));

        sc.close();

    }
}

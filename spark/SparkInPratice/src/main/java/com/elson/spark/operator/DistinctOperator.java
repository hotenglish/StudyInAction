package com.elson.spark.operator;

import org.apache.spark.SparkConf;
import org.apache.spark.api.java.JavaRDD;
import org.apache.spark.api.java.JavaSparkContext;

import java.util.Arrays;
import java.util.List;

public class DistinctOperator {

    public static void main(String args[]) {

        SparkConf conf = new SparkConf().setMaster("local").setAppName("DistinctOperator");

        JavaSparkContext sc = new JavaSparkContext(conf);

        List<String> names = Arrays.asList("xurunyun", "liangyongqi", "wangfei", "xurunyun");

        JavaRDD<String> nameRDD = sc.parallelize(names, 2);

        nameRDD.distinct().foreach(e -> System.out.println(e));

        sc.close();
    }

}

package com.elson.spark.operator;

import org.apache.spark.SparkConf;
import org.apache.spark.api.java.JavaRDD;
import org.apache.spark.api.java.JavaSparkContext;

import java.util.Arrays;
import java.util.List;

public class SampleOperator {

    public static void main(String args[]) {

        SparkConf conf = new SparkConf().setMaster("local").setAppName("SampleOperator");

        JavaSparkContext sc = new JavaSparkContext(conf);

        List<String> names = Arrays.asList("xurunyun0", "liangyongqi0", "wangfei0",
                "yasarka0", "xurunyun1", "liangyongqi1", "wangfei1",
                "yasarka1");

        JavaRDD<String> nameRDD = sc.parallelize(names);

        //nameRDD.sample(false, 0.5).foreach(e -> System.out.println(e));

        nameRDD.sample(true, 1L).foreach(e -> System.out.println(e));

        sc.close();

    }

}

package com.elson.spark.operator;

import org.apache.spark.SparkConf;
import org.apache.spark.api.java.JavaRDD;
import org.apache.spark.api.java.JavaSparkContext;

import java.util.Arrays;
import java.util.List;

/*
*
* sample(是否放回, fraction, seed)
* withReplacement：true抽取放回，false抽取不放回。
* fraction：
* 1）false抽取不放回的情况下，抽取的概率（0-1）。
* 0-全不抽
* 1-全抽
* 2）true抽取放回的情况下，抽取的次数。
seed：随机数种子。
*
* */
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

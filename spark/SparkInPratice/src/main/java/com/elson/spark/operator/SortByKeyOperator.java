package com.elson.spark.operator;

import org.apache.spark.SparkConf;
import org.apache.spark.api.java.JavaPairRDD;
import org.apache.spark.api.java.JavaSparkContext;
import scala.Tuple2;

import java.util.Arrays;
import java.util.List;

public class SortByKeyOperator {

    public static void main(String args[]) {

        SparkConf conf = new SparkConf().setMaster("local").setAppName("SortByKeyOperator");

        JavaSparkContext sc = new JavaSparkContext(conf);

        List<Tuple2<Integer, String>> scoreList = Arrays.asList(
                new Tuple2<>(150, "xurunyun"),
                new Tuple2<>(100, "liangyongqi"),
                new Tuple2<>(90, "wangfei"));

        JavaPairRDD<Integer, String> scores = sc.parallelizePairs(scoreList);

        JavaPairRDD<Integer, String> results = scores.sortByKey(false);

        results.foreach(tuple2 -> System.out.println(tuple2._2));

        sc.close();
    }
}

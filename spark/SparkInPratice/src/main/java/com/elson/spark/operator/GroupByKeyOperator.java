package com.elson.spark.operator;

import org.apache.spark.SparkConf;
import org.apache.spark.api.java.JavaPairRDD;
import org.apache.spark.api.java.JavaSparkContext;
import scala.Tuple2;

import java.util.Arrays;
import java.util.List;

public class GroupByKeyOperator {

    public static void main(String args[]) {

        SparkConf conf = new SparkConf().setMaster("local").setAppName("GroupByKeyOperator");

        JavaSparkContext sc = new JavaSparkContext(conf);

        List<Tuple2<String, Integer>> scoreList = Arrays.asList(
                new Tuple2<>("xurunyun", 150),
                new Tuple2<>("liangyongqi", 100),
                new Tuple2<>("wangfei", 100),
                new Tuple2<>("wangfei", 90));

        JavaPairRDD<String, Integer> rdd = sc.parallelizePairs(scoreList);

        rdd.groupByKey().foreach(e->System.out.println(e));

        sc.close();
    }
}

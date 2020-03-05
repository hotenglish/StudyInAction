package com.elson.spark.operator;

import org.apache.spark.SparkConf;
import org.apache.spark.api.java.JavaPairRDD;
import org.apache.spark.api.java.JavaSparkContext;
import scala.Tuple2;

import java.util.Arrays;
import java.util.List;

public class JoinOperator {

    public static void main(String args[]) {

        SparkConf conf = new SparkConf().setMaster("local").setAppName("JoinOperator");

        JavaSparkContext sc = new JavaSparkContext(conf);

        List<Tuple2<Integer, String>> studentList = Arrays.asList(
                new Tuple2<>(1, "xuruyun"),
                new Tuple2<>(2, "liangyongyi"),
                new Tuple2<>(3, "wangfei"),
                new Tuple2<>(3, "annie"));

        List<Tuple2<Integer, Integer>> scoreList = Arrays.asList(
                new Tuple2<>(1, 150),
                new Tuple2<>(2, 100),
                new Tuple2<>(3, 80),
                new Tuple2<>(3, 90));

        JavaPairRDD<Integer, String> students = sc.parallelizePairs(studentList);

        JavaPairRDD<Integer, Integer> scores = sc.parallelizePairs(scoreList);

        JavaPairRDD<Integer, Tuple2<String, Integer>> studentScores = students.join(scores);

        studentScores.foreach(e -> System.out.println(e._1 + " " + e._2));

    }

}

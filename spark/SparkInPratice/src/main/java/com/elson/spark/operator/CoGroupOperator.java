package com.elson.spark.operator;

import org.apache.spark.SparkConf;
import org.apache.spark.api.java.JavaPairRDD;
import org.apache.spark.api.java.JavaSparkContext;
import scala.Tuple2;

import java.util.Arrays;
import java.util.List;

public class CoGroupOperator {

    public static void main(String args[]) {

        SparkConf conf = new SparkConf().setMaster("local").setAppName("CoGroupOperator");

        JavaSparkContext sc = new JavaSparkContext(conf);

        List<Tuple2<String, String>> studentList = Arrays.asList(
                new Tuple2<>("1", "xuruyun"),
                new Tuple2<>("2", "wangfei"),
                new Tuple2<>("2", "liangyongqi"),
                new Tuple2<>("3", "lixin"));

        List<Tuple2<String, String>> scoreList = Arrays.asList(
                new Tuple2<>("1", "100"),
                new Tuple2<>("2", "90"),
                new Tuple2<>("3", "80"),
                new Tuple2<>("1", "70"),
                new Tuple2<>("2", "60"),
                new Tuple2<>("3", "50"));

        JavaPairRDD<String, String> students = sc.parallelizePairs(studentList);

        JavaPairRDD<String, String> scores = sc.parallelizePairs(scoreList);

        JavaPairRDD<String, Tuple2<Iterable<String>, Iterable<String>>> studentScores = students.cogroup(scores);

        studentScores.foreach(e -> System.out.println("student id:" + e._1 + "\n student name:" + e._2._1 + "\n student score:" + e._2._2));

        sc.close();
    }

}

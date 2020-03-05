package com.elson.spark.core;

import org.apache.spark.SparkConf;
import org.apache.spark.api.java.JavaPairRDD;
import org.apache.spark.api.java.JavaRDD;
import org.apache.spark.api.java.JavaSparkContext;
import scala.Tuple2;

import java.util.List;

public class TopN {

    public static void main(String args[]) {

        SparkConf config = new SparkConf().setMaster("local").setAppName("TopN");

        JavaSparkContext sc = new JavaSparkContext(config);

        JavaRDD<String> lines = sc.textFile("file:///home/oracle/learn/spark/SparkInPratice/02WorkCountJava/src/main/resources/top.txt");

        JavaPairRDD<Integer, String> pairs = lines.mapToPair(line -> new Tuple2<>(Integer.valueOf(line), line));

        List<String> results = pairs.sortByKey(false).map(item -> item._2).take(3);

        results.forEach(result -> System.out.println(result));

        sc.close();
    }

}

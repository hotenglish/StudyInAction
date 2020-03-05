package com.elson.spark.operator;

import org.apache.spark.SparkConf;
import org.apache.spark.api.java.JavaPairRDD;
import org.apache.spark.api.java.JavaRDD;
import org.apache.spark.api.java.JavaSparkContext;
import scala.Tuple2;

import java.util.Arrays;

public class AggregateByKeyOperator {

    public static void main(String args[]) {

        SparkConf config = new SparkConf().setMaster("local").setAppName("AggregateByKeyOperator");

        JavaSparkContext sc = new JavaSparkContext(config);

        JavaRDD<String> text = sc.textFile("file:///home/oracle/Downloads/words.txt");

        JavaRDD<String> words = text.flatMap(line -> Arrays.asList(line.split(" ")).iterator());

        JavaPairRDD<String, Integer> pairs = words.mapToPair(word -> new Tuple2<>(word, 1));

        JavaPairRDD<String, Integer> wordCounts =
                pairs.aggregateByKey(0, (Integer v1, Integer v2) -> v1 + v2,
                        (Integer v1, Integer v2) -> v1 + v2);

        wordCounts.foreach(tuple2 -> System.out.println("Words:" + tuple2._1 + " Count:" + tuple2._2));

        sc.close();

    }
}

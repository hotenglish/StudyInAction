package com.elson.spark;

import org.apache.spark.SparkConf;
import org.apache.spark.api.java.JavaPairRDD;
import org.apache.spark.api.java.JavaRDD;
import org.apache.spark.api.java.JavaSparkContext;
import scala.Tuple2;

import java.util.Arrays;

public class WordCountJava {

    public static void main(String args[]) {

        SparkConf config = new SparkConf().setMaster("local").setAppName("wc");

        JavaSparkContext sc = new JavaSparkContext(config);

        JavaRDD<String> text = sc.textFile("src/main/resources/words.txt");

        JavaRDD<String> words = text.flatMap(line -> Arrays.asList(line.split(" ")).iterator());

        JavaPairRDD<String, Integer> pairs = words.mapToPair(word -> new Tuple2<>(word, 1));

        JavaPairRDD<String, Integer> results
                = pairs.reduceByKey((Integer value1, Integer value2) -> value1 + value2);

        JavaPairRDD<Integer, String> temp
                = results.mapToPair(tuple -> new Tuple2<Integer, String>(tuple._2, tuple._1));

        JavaPairRDD<String, Integer> sorted
                = temp.sortByKey(false).mapToPair(tuple -> new Tuple2<String, Integer>(tuple._2, tuple._1));

        sorted.foreach(tuple -> System.out.println("word:" + tuple._1));

        sc.close();
    }

}

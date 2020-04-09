package com.elson.spark.streaming;

import org.apache.spark.SparkConf;
import org.apache.spark.streaming.Durations;
import org.apache.spark.streaming.api.java.JavaDStream;
import org.apache.spark.streaming.api.java.JavaPairDStream;
import org.apache.spark.streaming.api.java.JavaStreamingContext;
import scala.Tuple2;

import java.util.Arrays;

public class HDFSWordCount {

    public static void main(String args[]) throws InterruptedException {

        SparkConf sparkConf = new SparkConf().setMaster("local[1]").setAppName("HDFSWordCount");

        JavaStreamingContext jssc = new JavaStreamingContext(sparkConf, Durations.seconds(5));

        JavaDStream<String> lines = jssc.textFileStream("hdfs://node1:9000/wordcount_dir");

        JavaDStream<String> words = lines.flatMap(line -> Arrays.asList(line.split(" ")).iterator());

        JavaPairDStream<String, Integer> pairs = words.mapToPair(word -> new Tuple2<>(word, 1));

        JavaPairDStream<String, Integer> wordCount = pairs.reduceByKey((Integer v1, Integer v2) -> v1 + v2);

        wordCount.print();

        jssc.start();
        jssc.awaitTermination();
        jssc.close();

    }

}

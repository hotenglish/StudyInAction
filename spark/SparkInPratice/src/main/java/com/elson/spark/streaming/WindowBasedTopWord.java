package com.elson.spark.streaming;

import org.apache.spark.SparkConf;
import org.apache.spark.streaming.Durations;
import org.apache.spark.streaming.api.java.JavaDStream;
import org.apache.spark.streaming.api.java.JavaPairDStream;
import org.apache.spark.streaming.api.java.JavaReceiverInputDStream;
import org.apache.spark.streaming.api.java.JavaStreamingContext;
import scala.Tuple2;

public class WindowBasedTopWord {

    public static void main(String args[]) throws InterruptedException {

        SparkConf sparkConf = new SparkConf().setMaster("local[2]").setAppName("WindowBasedTopWord");

        JavaStreamingContext jssc = new JavaStreamingContext(sparkConf, Durations.seconds(5));

        jssc.checkpoint(".");

        JavaReceiverInputDStream<String> log = jssc.socketTextStream("master", 8089);

        JavaDStream<String> searchWord = log.map(searchLog -> searchLog.split(" ")[1]);

        JavaPairDStream<String, Integer> pairs = searchWord.mapToPair(word -> new Tuple2<>(word, 1));

        JavaPairDStream<String, Integer> wordCounts = pairs.reduceByKeyAndWindow((Integer v1, Integer v2) -> v1 + v2,
                (Integer v1, Integer v2) -> v1 - v2, Durations.seconds(60), Durations.seconds(10));

        wordCounts.print();

        jssc.awaitTermination();

        jssc.stop();

    }
}

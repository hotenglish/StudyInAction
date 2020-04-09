package com.elson.spark.streaming;

import org.apache.spark.SparkConf;
import org.apache.spark.api.java.Optional;
import org.apache.spark.streaming.Durations;
import org.apache.spark.streaming.api.java.JavaDStream;
import org.apache.spark.streaming.api.java.JavaPairDStream;
import org.apache.spark.streaming.api.java.JavaReceiverInputDStream;
import org.apache.spark.streaming.api.java.JavaStreamingContext;
import scala.Tuple2;

import java.util.List;

import java.util.Arrays;


public class UpdateStateByKeyWordCount {

    public static void main(String args[]) throws InterruptedException {

        SparkConf sparkConf = new SparkConf().setMaster("local[2]").setAppName("UpdateStateByKeyWordCount");

        JavaStreamingContext jssc = new JavaStreamingContext(sparkConf, Durations.seconds(5));

        jssc.checkpoint(".");

        JavaReceiverInputDStream<String> lines = jssc.socketTextStream("node1", 8089);

        JavaDStream<String> words = lines.flatMap(line -> Arrays.asList(line.split(" ")).iterator());

        JavaPairDStream<String, Integer> pairs = words.mapToPair(word -> new Tuple2<>(word, 1));

        JavaPairDStream<String, Integer> wordCount
                = pairs.updateStateByKey((List<Integer> values, Optional<Integer> state) -> {
            Integer newValue = 0;
            if (state.isPresent()) {
                newValue = state.get();
            }
            for (Integer value : values) {
                newValue += value;
            }
            return Optional.of(newValue);
        });

        wordCount.print();

        jssc.start();

        jssc.awaitTermination();

        jssc.close();

    }

}

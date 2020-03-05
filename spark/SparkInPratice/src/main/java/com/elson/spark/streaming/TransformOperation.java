package com.elson.spark.streaming;

import org.apache.spark.SparkConf;
import org.apache.spark.api.java.JavaPairRDD;
import org.apache.spark.api.java.JavaRDD;
import org.apache.spark.streaming.Durations;
import org.apache.spark.streaming.api.java.JavaDStream;
import org.apache.spark.streaming.api.java.JavaPairDStream;
import org.apache.spark.streaming.api.java.JavaReceiverInputDStream;
import org.apache.spark.streaming.api.java.JavaStreamingContext;
import scala.Tuple2;

import java.util.ArrayList;
import java.util.List;

import org.apache.spark.api.java.Optional;

/**
 * 过滤黑名单
 * transform操作
 * DStream可以通过transform做RDD到RDD的任意操作。
 *
 * @author root
 */
public class TransformOperation {

    public static void main(String args[]) throws InterruptedException {

        SparkConf sparkConf = new SparkConf().setMaster("local[2]").setAppName("TransformOperation");

        JavaStreamingContext jssc = new JavaStreamingContext(sparkConf, Durations.seconds(5));

        //模拟黑名单
        List<Tuple2<String, Boolean>> blackList = new ArrayList<>();

        blackList.add(new Tuple2<>("yasaka", true));
        blackList.add(new Tuple2<>("xuruyun", false));

        //将黑名单转换成RDD
        final JavaPairRDD<String, Boolean> blackNameRDD = jssc.sparkContext().parallelizePairs(blackList);

        //接受socket数据源
        JavaReceiverInputDStream<String> adsClickLogStream = jssc.socketTextStream("Master", 8090);

        JavaPairDStream<String, String> adsClickLogPairStream
                = adsClickLogStream.mapToPair(line -> new Tuple2<>(line.split(" ")[2], line));

        JavaDStream<String> normalLogs = adsClickLogPairStream.transform(userLogBatchRDD -> {

            JavaPairRDD<String, Tuple2<String, Optional<Boolean>>> joinedRDD
                    = userLogBatchRDD.leftOuterJoin(blackNameRDD);

            JavaPairRDD<String, Tuple2<String, Optional<Boolean>>> filteredRDD = joinedRDD.filter(tuple2 -> {
                if (tuple2._2._2.isPresent() && tuple2._2._2.get()) {
                    return false;
                }
                return true;
            });

            JavaRDD<String> validLogRDD = filteredRDD.map(tuple2 -> tuple2._2._1);

            return validLogRDD;
        });

        normalLogs.print();

        jssc.start();

        jssc.awaitTermination();

        jssc.close();

    }

}

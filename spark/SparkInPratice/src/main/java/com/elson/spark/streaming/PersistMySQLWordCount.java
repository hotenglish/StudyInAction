package com.elson.spark.streaming;

import org.apache.spark.SparkConf;
import org.apache.spark.streaming.Durations;
import org.apache.spark.streaming.api.java.JavaDStream;
import org.apache.spark.streaming.api.java.JavaPairDStream;
import org.apache.spark.streaming.api.java.JavaStreamingContext;
import scala.Tuple2;

import java.sql.Connection;
import java.sql.Statement;
import java.util.Arrays;

public class PersistMySQLWordCount {

    public static void main(String args[]) throws InterruptedException {

        SparkConf sparkConf = new SparkConf().setMaster("local[1]").setAppName("PersistMySQLWordCount");

        JavaStreamingContext jssc = new JavaStreamingContext(sparkConf, Durations.seconds(3));

        JavaDStream<String> lines = jssc.textFileStream("hdfs://Master:9000/wordcount_dir");

        JavaDStream<String> words = lines.flatMap(line -> Arrays.asList(line.split(" ")).iterator());

        JavaPairDStream<String, Integer> pairs = words.mapToPair(word -> new Tuple2<>(word, 1));

        JavaPairDStream<String, Integer> wordCount = pairs.reduceByKey((Integer v1, Integer v2) -> v1 + v2);

        wordCount.print();

        wordCount.foreachRDD(wordCountRDD -> {

            wordCountRDD.foreachPartition(p -> {

                Connection conn = ConnectionPool.getConnection();

                Tuple2<String, Integer> tuple2 = null;

                while (p.hasNext()) {

                    tuple2 = p.next();

                    String sql = "insert into wordcount(word,numcount) " +
                            "values('" + tuple2._1 + "'," + tuple2._2 + ")";

                    System.out.println(sql);

                    Statement stat = conn.createStatement();

                    stat.executeUpdate(sql);

                }

                ConnectionPool.returnConnection(conn);

            });

        });

        jssc.start();
        jssc.awaitTermination();
        jssc.close();

    }

}

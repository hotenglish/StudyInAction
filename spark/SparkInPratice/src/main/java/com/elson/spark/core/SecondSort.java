package com.elson.spark.core;

import org.apache.spark.SparkConf;
import org.apache.spark.api.java.JavaPairRDD;
import org.apache.spark.api.java.JavaRDD;
import org.apache.spark.api.java.JavaSparkContext;
import scala.Tuple2;

public class SecondSort {

    public static void main(String args[]) {

        SparkConf conf = new SparkConf().setMaster("local").setAppName("SecondSortKey");

        JavaSparkContext sc = new JavaSparkContext(conf);

        JavaRDD<String> lines = sc.textFile("file:///home/oracle/learn/spark/SparkInPratice/02WorkCountJava/src/main/resources/sort.txt");

        JavaPairRDD<SecondSortKey, String> pairs = lines.mapToPair(line -> {
            String[] lineSplited = line.split(" ");
            SecondSortKey key = new SecondSortKey();
            key.setFirst(Integer.valueOf(lineSplited[0]));
            key.setSecond(Integer.valueOf(lineSplited[2]));
            return new Tuple2<>(key, line);
        });

        JavaPairRDD<SecondSortKey, String> sortedPairs = pairs.sortByKey(false);

        JavaRDD<String> results = sortedPairs.map(tuple2 -> tuple2._2);

        results.foreach(e -> System.out.println(e));

        sc.close();

    }


}

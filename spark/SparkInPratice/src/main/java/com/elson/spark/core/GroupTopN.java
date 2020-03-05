package com.elson.spark.core;

import org.apache.spark.SparkConf;
import org.apache.spark.api.java.JavaPairRDD;
import org.apache.spark.api.java.JavaRDD;
import org.apache.spark.api.java.JavaSparkContext;
import scala.Tuple2;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;

public class GroupTopN {

    public static void main(String args[]) {

        SparkConf conf = new SparkConf().setMaster("local").setAppName("GroupTopN");

        JavaSparkContext sc = new JavaSparkContext(conf);

        JavaRDD<String> lines = sc.textFile("file:///home/oracle/learn/spark/SparkInPratice/02WorkCountJava/src/main/resources/score.txt");

        JavaPairRDD<String, Integer> pairs = lines.mapToPair(line -> {
            String[] arr = line.split(" ");
            return new Tuple2<>(arr[0], Integer.valueOf(arr[1]));
        });

        JavaPairRDD<String, Iterable<Integer>> groupPairs = pairs.groupByKey();

        JavaPairRDD<String, Iterable<Integer>> top2Score = groupPairs.mapToPair(tuple2 -> {

            List<Integer> list = new ArrayList<>();

            Iterator<Integer> it = tuple2._2.iterator();

            while (it.hasNext()) {
                Integer score = it.next();
                list.add(score);
            }

            Collections.sort(list, (Integer o1, Integer o2) -> -(o1 - o2));

            list = list.subList(0, 2);

            return new Tuple2<>(tuple2._1, list);

        });

        top2Score.foreach(tuple -> System.out.println(" Group Id:" + tuple._1 + " Group values" + tuple._2));

        sc.close();

    }

}

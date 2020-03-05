package com.elson.spark.operator;

import org.apache.spark.SparkConf;
import org.apache.spark.api.java.JavaPairRDD;
import org.apache.spark.api.java.JavaSparkContext;
import scala.Tuple2;

import java.util.Arrays;
import java.util.List;
import java.util.Map;

public class CountByKeyOperator {

    public static void main(String args[]) {

        SparkConf conf = new SparkConf().setAppName("CountByKeyOperator").setMaster("local[3]");

        JavaSparkContext sc = new JavaSparkContext(conf);

        List<Tuple2<String, String>> scoreList = Arrays.asList(
                new Tuple2<>("70s", "xurunyun"),
                new Tuple2<>("70s", "wangfei"),
                new Tuple2<>("70s", "wangfei"),
                new Tuple2<>("80s", "yasaka"),
                new Tuple2<>("80s", "zhengzhouwu"),
                new Tuple2<>("80s", "xilin"));

        JavaPairRDD<String, String> students = sc.parallelizePairs(scoreList);

        Map<String, Long> counts = students.countByKey();

        counts.entrySet().forEach(e -> System.out.println(e.getKey() + ":" + e.getValue()));

        sc.close();

    }

}

package com.elson.spark.operator;

import org.apache.spark.SparkConf;
import org.apache.spark.api.java.JavaPairRDD;
import org.apache.spark.api.java.JavaRDD;
import org.apache.spark.api.java.JavaSparkContext;

import java.util.Arrays;
import java.util.List;

public class CartesianOperator {


    public static void main(String args[]) {

        SparkConf conf = new SparkConf().setMaster("local").setAppName("CartesianOperator");

        JavaSparkContext sc = new JavaSparkContext(conf);

        List<String> cloths = Arrays.asList("A", "B", "C", "D");

        List<String> trousers = Arrays.asList("E", "F", "G", "H");

        JavaRDD<String> clothsRDD1 = sc.parallelize(cloths);

        JavaRDD<String> trousersRDD2 = sc.parallelize(trousers);

        JavaPairRDD<String, String> pairs = clothsRDD1.cartesian(trousersRDD2);

        pairs.foreach(e -> System.out.println(e));

        sc.close();

    }
}

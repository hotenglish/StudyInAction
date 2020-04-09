package com.elson.spark.operator;

import org.apache.spark.SparkConf;
import org.apache.spark.api.java.JavaPairRDD;
import org.apache.spark.api.java.JavaRDD;
import org.apache.spark.api.java.JavaSparkContext;
import scala.Tuple2;

import java.util.Arrays;

/*
* 算子释义：
* aggregateByKey， 先说分为三个参数的：
* 第一个参数是， 每个key的初始值
* 第二个是个函数， Seq Function， 经测试这个函数就是用来先对每个分区内的数据按照key分别进行定义进行函数定义的操作
* 第三个是个函数， Combiner Function， 对经过 Seq Function 处理过的数据按照key分别进行进行函数定义的操作
* Spark中的groupByKey,reduceByKey,combineBykey,和aggregateByKey的比较和区别
* https://blog.csdn.net/sperospera/article/details/89164261
* */

public class AggregateByKeyOperator {

    public static void main(String args[]) {

        SparkConf config = new SparkConf().setMaster("local").setAppName("AggregateByKeyOperator");

        JavaSparkContext sc = new JavaSparkContext(config);

        JavaRDD<String> text = sc.textFile("src/main/resources/words.txt");

        JavaRDD<String> words = text.flatMap(line -> Arrays.asList(line.split(" ")).iterator());

        JavaPairRDD<String, Integer> pairs = words.mapToPair(word -> new Tuple2<>(word, 1));

        JavaPairRDD<String, Integer> wordCounts =
                pairs.aggregateByKey(0, (Integer v1, Integer v2) -> v1 + v2,
                        (Integer v1, Integer v2) -> v1 + v2);

        wordCounts.foreach(tuple2 -> System.out.println("Words:" + tuple2._1 + " Count:" + tuple2._2));

        sc.close();

    }
}

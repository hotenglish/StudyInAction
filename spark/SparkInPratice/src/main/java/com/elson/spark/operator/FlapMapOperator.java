package com.elson.spark.operator;

import org.apache.spark.SparkConf;
import org.apache.spark.api.java.JavaRDD;
import org.apache.spark.api.java.JavaSparkContext;

import java.util.Arrays;
import java.util.List;

public class FlapMapOperator {

    public static void main(String args[]) {
        SparkConf config = new SparkConf()
                .setMaster("local")
                .setAppName("FlapMapOperator");
        JavaSparkContext sc = new JavaSparkContext(config);

        List<String> lineList = Arrays.asList("hello xurunyun", "hello liangyongqi", "hello wangfei");

        JavaRDD<String> lines = sc.parallelize(lineList);

        JavaRDD<String> words = lines.flatMap(line -> Arrays.asList(line.split(" ")).iterator());

        words.foreach(word -> System.out.println(word));

        sc.close();
    }

}

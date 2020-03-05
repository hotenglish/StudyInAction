package com.elson.spark.operator;

import org.apache.spark.SparkConf;
import org.apache.spark.api.java.JavaRDD;
import org.apache.spark.api.java.JavaSparkContext;

import java.util.*;

//理解里面final使用原因

public class MapPartitionsOperator {

    public static void main(String args[]) {
        SparkConf conf = new SparkConf().setMaster("local").setAppName("MapPartitionsOperator");
        JavaSparkContext sc = new JavaSparkContext(conf);
        List<String> names = Arrays.asList("xurunyun", "liangyongqi", "wangfei");
        JavaRDD<String> nameRDD = sc.parallelize(names);
        final Map<String, Integer> scoreMap = new HashMap<>();
        scoreMap.put("xurunyun", 150);
        scoreMap.put("liangyongqi", 100);
        scoreMap.put("wangfei", 90);

        //mapPartitions
        //map 一次就处理一个partitions的一个数据！！！
        //mapPartitions 一次就处理一个partition中的所有数据！！！

        //推荐的使用场景!!!
        //如果你的RDD的数据不是特别多，那采用mapPartitions算子代替map，可以加快处理速度
        //比如说，100亿条数据，你一个Partition里面就有10亿条数据，不建议使用mapPartitions，
        //内存溢出

        //Input FlatMapFunction[T,R] T-> Iterator<R>
        JavaRDD<Integer> scoreRDD = nameRDD.mapPartitions(iterator -> {
            List<Integer> list = new ArrayList<>();
            while (iterator.hasNext()) {
                String name = iterator.next();
                Integer score = scoreMap.get(name);
                list.add(score);
            }
            return list.iterator();
        });
        scoreRDD.foreach(e -> System.out.println(e));
        sc.close();
    }

}

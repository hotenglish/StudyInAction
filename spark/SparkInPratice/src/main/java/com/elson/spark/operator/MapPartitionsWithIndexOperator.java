package com.elson.spark.operator;

import org.apache.spark.SparkConf;
import org.apache.spark.api.java.JavaRDD;
import org.apache.spark.api.java.JavaSparkContext;

import java.util.*;

public class MapPartitionsWithIndexOperator {

    public static void main(String args[]) {
        SparkConf conf = new SparkConf().setMaster("local").setAppName("MapPartitionsWithIndexOperator");
        conf.set("spark.default.parellelism", "3");
        JavaSparkContext sc = new JavaSparkContext(conf);
        List<String> names = Arrays.asList("xurunyun", "liangyongqi", "wangfei");
        JavaRDD<String> nameRDD = sc.parallelize(names, 2);
        //其實這裏不寫2，默其實也是2
        //parallelize 一次就处理一个partitions的一个数据！！！
        //mapPartitions 一次就处理一个partition中的所有数据！！！

        //推荐的使用场景!!!
        //如果你的RDD的数据不是特别多，那采用mapPartitions算子代替map，可以加快处理速度
        //比如说，100亿条数据，你一个Partition里面就有10亿条数据，不建议使用mapPartitions，
        //内存溢出

        //Input Function2<T1, T2, R>   [INTEGER, ITERATOR<T>, Iterator<R>]-> Iterator<R>
        JavaRDD<String> mapPartitionsWithIndex = nameRDD.mapPartitionsWithIndex((Integer index, Iterator<String> iterator) -> {
            List<String> list = new ArrayList<>();
            while (iterator.hasNext()) {
                String name = iterator.next();
                String result = index + ":" + name;
                list.add(result);
            }
            return list.iterator();
        }, true);

        mapPartitionsWithIndex.foreach(e -> System.out.println(e));
        sc.close();
    }

}

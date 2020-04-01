package com.elson.spark.operator;

import org.apache.spark.SparkConf;
import org.apache.spark.api.java.JavaRDD;
import org.apache.spark.api.java.JavaSparkContext;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;

/*
* 通过源码可以看出两者的区别：coalesce()方法的参数shuffle默认设置为false，
* repartition()方法就是coalesce()方法shuffle为true的情况。
*
* 使用情景
*    假设RDD有N个分区，需要重新划分成M个分区：
*    1. N < M: 一般情况下N个分区有数据分布不均匀的状况，利用HashPartitioner函数将数据重新分区为M个，
*    这时需要将shuffle设置为true。因为重分区前后相当于宽依赖，会发生shuffle过程，
*    此时可以使用coalesce(shuffle=true)，或者直接使用repartition()。
*
*    2. 如果N > M并且N和M相差不多(假如N是1000，M是100):
*    那么就可以将N个分区中的若干个分区合并成一个新的分区，最终合并为M个分区，这是前后是窄依赖关系，
*    可以使用coalesce(shuffle=false)。
*
*    3. 如果 N> M并且两者相差悬殊: 这时如果将shuffle设置为false，父子RDD是窄依赖关系，他们同处在一个 STAGE中，
*    就可能造成spark程序的并行度不够，从而影响性能，如果在M为1的时候，为了使coalesce之前的操作有更好的并行度，
*    可以将shuffle设置为true。
*
* 总结
*    如果传入的参数大于现有的分区数目，而shuffle为false，RDD的分区数不变，也就是说不经过shuffle，是无法将RDD
* 分区数变多的。
*/
public class CoalesceOperator {

    public static void main(String args[]) {
        SparkConf conf = new SparkConf().setMaster("local").setAppName("CoalesceOperator");
        conf.set("spark.default.parellelism", "3");
        JavaSparkContext sc = new JavaSparkContext(conf);
        List<String> names = Arrays.asList("xurunyun1", "xurunyun2", "xurunyun3",
                "xurunyun4", "xurunyun5", "xurunyun6",
                "xurunyun7", "xurunyun8", "xurunyun9",
                "xurunyun10", "xurunyun11", "xurunyun12");
        JavaRDD<String> staffRDD = sc.parallelize(names, 6);
        JavaRDD<String> staffRDD2 = staffRDD.mapPartitionsWithIndex((Integer index, Iterator<String> iterator) -> {
            List<String> list = new ArrayList<>();
            while (iterator.hasNext()) {
                String staff = iterator.next();
                list.add("Department[" + (index + 1) + "]" + staff);
            }
            return list.iterator();
        }, true);

        staffRDD2.foreach(e -> System.out.println(e));

        JavaRDD<String> staffRDD3 = staffRDD2.coalesce(3);
        JavaRDD<String> staffRDD4 = staffRDD3.mapPartitionsWithIndex((Integer index, Iterator<String> iterator) -> {
            List<String> list = new ArrayList<>();
            while (iterator.hasNext()) {
                String staff = iterator.next();
                list.add("Department[" + (index + 1) + "]" + staff);
            }
            return list.iterator();
        }, true);

        staffRDD4.foreach(e -> System.out.println(e));
        sc.close();
    }

}

package chapter13

import org.apache.spark.{SparkConf, SparkContext}

object ReduceByKeyExample {

  def main(args: Array[String]): Unit = {

    val conf = new SparkConf

    conf.setAppName("My First Spark App")

    conf.setMaster("local")

    val sc = new SparkContext(conf)

    val rdd = sc.parallelize(Array(("V1", 1), ("V2", 2), ("V2", 2), ("U3", 1), ("U1", 1), ("U2", 2), ("V2", 2), ("U3", 1)))

    val reduceByKey = rdd.reduceByKey(_ + _)

    val reduceByKeyRdd = rdd.reduceByKey(_ + _)

    println(reduceByKeyRdd.foreach({ e => println(e) }))

  }

}

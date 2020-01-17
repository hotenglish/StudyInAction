package chapter13

import org.apache.spark.{SparkConf, SparkContext}

object RddMapPartitions {

  def main(args: Array[String]): Unit = {

    val conf = new SparkConf

    conf.setAppName("My First Spark App")

    conf.setMaster("local")

    val sc = new SparkContext(conf)

    val rdd = sc.parallelize(1 to 10)

    val mapPartitionsRdd = rdd.mapPartitions(iter => iter.filter(_ > 3))

    mapPartitionsRdd.collect().foreach(e => println(e))

    sc.stop()

  }

}

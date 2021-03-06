package chapter13.rddinaction

import org.apache.spark.{SparkConf, SparkContext}

object RddDistinctExample {

  def main(args: Array[String]): Unit = {

    val conf = new SparkConf

    conf.setAppName("My First Spark App")

    conf.setMaster("local")

    val sc = new SparkContext(conf)

    val rdd = sc.parallelize(Array(1, 2, 2, 4, 5, 5, 7, 6, 8))

    val distinctRDD = rdd.distinct(2)

    distinctRDD.collect().foreach(e => print(e + " "))

    println()

    sc.stop()

  }

}

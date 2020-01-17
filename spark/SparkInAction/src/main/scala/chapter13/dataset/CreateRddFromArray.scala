package chapter13.dataset

import org.apache.spark.{SparkConf, SparkContext}

object CreateRddFromArray {

  def main(args: Array[String]): Unit = {

    val conf = new SparkConf

    conf.setAppName("My First Spark App")

    conf.setMaster("local")

    val sc = new SparkContext(conf)

    val numbers = 1 to 20

    val rdd = sc.makeRDD(numbers)

    println(rdd.foreach(e => println(e)))

  }

}

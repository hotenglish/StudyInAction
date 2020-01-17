package chapter13.rddinaction

import org.apache.spark.{SparkConf, SparkContext}

object RddApiFlatMapExample {

  def main(args: Array[String]): Unit = {

    val conf = new SparkConf

    conf.setAppName("My First Spark App")

    conf.setMaster("local")

    val sc = new SparkContext(conf)

    val rdd = sc.textFile("src/README.md", 1)

    val flatMapRdd = rdd.flatMap { line => line.split(" ") }

    flatMapRdd.foreach(e => println(e))

    sc.stop()

  }

}

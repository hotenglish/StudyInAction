package chapter11

import org.apache.spark.{SparkConf, SparkContext}

object WordCount {

  def main(args: Array[String]): Unit = {

    val conf = new SparkConf

    conf.setAppName("My First Spark App")

    conf.setMaster("local")

    val sc = new SparkContext(conf)

    val lines = sc.textFile("src/README.md", 1)

    val words = lines.flatMap { line => line.split(" ") }

    val pairs = words.map { word => (word, 1) }

    val wordCounts = pairs.reduceByKey(_ + _)

    wordCounts.foreach(pair => println(pair._1 + ":" + pair._2))

    sc.stop()

  }

}

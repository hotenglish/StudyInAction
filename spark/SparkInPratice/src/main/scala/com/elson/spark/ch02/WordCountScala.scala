package com.elson.spark.ch02

import org.apache.spark.{SparkConf, SparkContext}

object WordCountScala {

  def main(args: Array[String]): Unit = {
    val conf = new SparkConf().setMaster("local").setAppName("wc")
    val sc = new SparkContext(conf)
    val text = sc.textFile("file:///home/oracle/Downloads/words.txt")
    val words = text.flatMap { line => line.split(" ") }
    val pairs = words.map { word => (word, 1) }
    val results = pairs.reduceByKey(_ + _).map(tuple => (tuple._2, tuple._1))
    val sorted = results.sortByKey(false).map(tuple => (tuple._2, tuple._1))
    sorted.foreach(x => println(x))
  }

}

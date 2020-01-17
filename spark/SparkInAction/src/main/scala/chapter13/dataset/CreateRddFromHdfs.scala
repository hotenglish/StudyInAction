package chapter13.dataset

import org.apache.spark.{SparkConf, SparkContext}

object CreateRddFromHdfs {

  def main(args: Array[String]): Unit = {

    val conf = new SparkConf

    conf.setAppName("My First Spark App")

    conf.setMaster("local")

    val sc = new SparkContext(conf)

    val rdd = sc.textFile("hdfs://master:9000/spark/data", 1)

  }

}

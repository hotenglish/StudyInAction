package chapter13.dataset

import org.apache.spark.{SparkConf, SparkContext}

object CreateRddFromLocalFile {

  def main(args: Array[String]): Unit = {

    val conf = new SparkConf

    conf.setAppName("My First Spark App")

    conf.setMaster("local")

    val sc = new SparkContext(conf)

    val rdd = sc.textFile("src/README.md", 1)

    println("rdd.partitions.size=>" + rdd.partitions.size)

  }

}

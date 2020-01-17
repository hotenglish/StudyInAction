package chapter13

import org.apache.spark.{SparkConf, SparkContext}

object RddApiMapExample {

  def main(args: Array[String]): Unit = {

    val conf = new SparkConf

    conf.setAppName("My First Spark App")

    conf.setMaster("local")

    val sc = new SparkContext(conf)

    val rdd = sc.parallelize(1 to 100)

    println(rdd.partitions.size)

    val mapRdd = rdd.map(_ * 2)

    println(mapRdd.partitions.size)

    sc.stop()

  }

}

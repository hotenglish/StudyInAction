package chapter13

import org.apache.spark.{SparkConf, SparkContext}

object RddApiFilterExample {

  def main(args: Array[String]): Unit = {

    val conf = new SparkConf

    conf.setAppName("My First Spark App")

    conf.setMaster("local")

    val sc = new SparkContext(conf)

    val rdd = sc.parallelize(1 to 10)

    val filterRdd = rdd.filter(_ % 2 == 0)

    filterRdd.collect().foreach(e=>println(e))

    sc.stop()

  }

}

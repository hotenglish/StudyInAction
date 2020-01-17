package chapter13.rddinaction

import org.apache.spark.{SparkConf, SparkContext}

object MapValuesExample {

  def main(args: Array[String]): Unit = {

    val conf = new SparkConf

    conf.setAppName("My First Spark App")

    conf.setMaster("local")

    val sc = new SparkContext(conf)

    val rdd = sc.parallelize(Array(("A", 1), ("B", 2), ("C", 3)))

    val mapValueRDD = rdd.mapValues(a => a * 2)

    mapValueRDD.collect().foreach(e => println(e + " "))

    sc.stop()

  }

}

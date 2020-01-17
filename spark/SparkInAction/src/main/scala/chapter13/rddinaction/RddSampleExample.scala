package chapter13.rddinaction

import org.apache.spark.{SparkConf, SparkContext}

object RddSampleExample {

  def main(args: Array[String]): Unit = {

    val conf = new SparkConf

    conf.setAppName("My First Spark App")

    conf.setMaster("local")

    val sc = new SparkContext(conf)

    val rdd1 = sc.parallelize(Array("A", "B", "C", "D"))

    val sampleRDD = rdd1.sample(true, 0.6, 5)

    sampleRDD.collect().foreach(e => print(e + " "))

    println()

    sc.stop()

  }

}

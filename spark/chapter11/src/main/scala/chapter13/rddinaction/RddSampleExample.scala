package chapter13

import org.apache.spark.{SparkConf, SparkContext}

object RddSampleExample {

  def main(args: Array[String]): Unit = {

    val conf = new SparkConf

    conf.setAppName("My First Spark App")

    conf.setMaster("local")

    val sc = new SparkContext(conf)

    val rdd1 = sc.parallelize(Array("A", "B", "C", "D"))

    val sampleRDD = rdd1.sample(true, 0.8, 5)

    println(sampleRDD.collect().foreach({e => println(e)}));

    sc.stop()

  }

}

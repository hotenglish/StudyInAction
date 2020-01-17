package chapter13

import org.apache.spark.{SparkConf, SparkContext}

object RddJoinExample {

  def main(args: Array[String]): Unit = {

    val conf = new SparkConf

    conf.setAppName("My First Spark App")

    conf.setMaster("local")

    val sc = new SparkContext(conf)

    val rdd1 = sc.parallelize(Array(("V1", 1), ("V1", 2), ("V2", 2), ("U1", 1), ("U1", 2), ("U1", 3), ("U5", 4)))

    val rdd2 = sc.parallelize(Array(("V1", 1), ("V1", 2), ("V8", 2), ("U1", 2), ("U5", 1)))

    val joinRdd = rdd1.join(rdd2)

    println(joinRdd.foreach({ e => println(e) }))

  }

}

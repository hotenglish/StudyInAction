package chapter13

import org.apache.spark.{HashPartitioner, SparkConf, SparkContext}

object PartitionByExample {

  def main(args: Array[String]): Unit = {

    val conf = new SparkConf

    conf.setAppName("My First Spark App")

    conf.setMaster("local")

    val sc = new SparkContext(conf)

    val rdd = sc.parallelize(Array(("V1", 2), ("V1", 1), ("V2", 2), ("W3", 1), ("U1", 2), ("U1", 1), ("U1", 3)))

    val partitionByRdd = rdd.partitionBy(new HashPartitioner(3))

    println(partitionByRdd.foreach({ e => println(e) }))

  }

}

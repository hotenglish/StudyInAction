package com.elson.spark.operator

import org.apache.spark.{SparkConf, SparkContext}

object AggregateByKeyOperatorScala {

  def main(args: Array[String]): Unit = {
    val conf = new SparkConf().setMaster("local").setAppName("AggregateByKeyOperatorScala")
    val sc = new SparkContext(conf)
    testAggregateByKey1(sc)
    print("-------------------------")
    testAggregateByKey2(sc)
  }

  private def testAggregateByKey1(sc: SparkContext) = {

    var data = sc.parallelize(List((1, 3), (1, 2), (1, 4), (2, 3), (3, 6), (3, 8)), 1)

    def seq(a: Int, b: Int): Int = {
      println("seq: " + a + "\t " + b)
      math.max(a, b)
    }

    def comb(a: Int, b: Int): Int = {
      println("comb: " + a + "\t " + b)
      a + b
    }

    data.aggregateByKey(0)(seq, comb).collect.foreach(println)
  }

  private def testAggregateByKey2(sc: SparkContext) = {
    var data = sc.parallelize(List((1, 3), (1, 2), (1, 4), (2, 3), (3, 6), (3, 8)), 3)

    def seq(a: Int, b: Int): Int = {
      println("seq: " + a + "\t " + b)
      math.max(a, b)
    }

    def comb(a: Int, b: Int): Int = {
      println("comb: " + a + "\t " + b)
      a + b
    }

    data.aggregateByKey(0)(seq, comb).collect.foreach(println)
  }
}
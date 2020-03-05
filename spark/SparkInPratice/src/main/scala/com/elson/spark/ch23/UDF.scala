package com.elson.spark.ch23

import org.apache.spark.sql.types.{StringType, StructField, StructType}
import org.apache.spark.sql.{Row, SparkSession}

object UDF {

  def main(array: Array[String]): Unit = {

    var sparkSession = SparkSession.builder().master("local").appName("UDF").getOrCreate()

    var names = Array("xurunyun", "liangyongqi", "wangfei", "xurunyun")

    var nameRDD = sparkSession.sparkContext.parallelize(names, 4)

    var nameRowRDD = nameRDD.map(name => Row(name))

    var structType = StructType(Array(StructField("name", StringType, true)))

    var nameDS = sparkSession.createDataFrame(nameRowRDD, structType)

    //Use createOrReplaceTempView(viewName) instead. 2.0 version
    nameDS.createOrReplaceTempView("names");

    sparkSession.udf.register("strLength", (str: String) => str.length + 10)

    sparkSession.sql("select name,strLength(name) from names").collect().foreach(println)

    sparkSession.close()

  }

}

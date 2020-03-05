package com.elson.spark.ch23

import org.apache.spark.sql.{Row, SparkSession}
import org.apache.spark.sql.types.{StringType, StructField, StructType}

object UDAF {

  def main(array: Array[String]): Unit = {

    var sparkSession = SparkSession.builder().master("local").appName("UDF").getOrCreate()

    var names = Array("yasaka", "xurunyun", "wangfei", "liangyongqi", "xurunyun", "xurunyun")

    var nameRDD = sparkSession.sparkContext.parallelize(names, 4)

    var nameRowRDD = nameRDD.map(name => Row(name))

    var structType = StructType(Array(StructField("name", StringType, true)))

    var nameDS = sparkSession.createDataFrame(nameRowRDD, structType)

    //Use createOrReplaceTempView(viewName) instead. 2.0 version
    nameDS.createOrReplaceTempView("names")

    sparkSession.udf.register("strGroupCount", new StringGroupCount)

    sparkSession.sql("select name,strGroupCount(name) from names group by name").collect().foreach(println)

    sparkSession.close()

  }

}

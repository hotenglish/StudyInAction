package com.elson.spark.sql;

import org.apache.spark.SparkConf;
import org.apache.spark.api.java.JavaSparkContext;
import org.apache.spark.sql.Dataset;
import org.apache.spark.sql.Row;
import org.apache.spark.sql.SQLContext;
import org.apache.spark.sql.SaveMode;

public class SaveModeTest {

    public static void main(String args[]) throws InterruptedException {

        SparkConf conf = new SparkConf().setMaster("local").setAppName("SaveModeTest");

        JavaSparkContext sc = new JavaSparkContext(conf);

        SQLContext sqlContext = new SQLContext(sc);

        Dataset<Row> userDS = sqlContext.read().format("json").load("file:///home/oracle/learn/spark/SparkInPratice/02WorkCountJava/src/main/resources/people.json");

        //userDS.write().mode(SaveMode.ErrorIfExists).parquet("parquet.res");

        //userDS.write().mode(SaveMode.Append).parquet("parquet.res");

        userDS.write().mode(SaveMode.Ignore).parquet("parquet.res");

        //userDS.write().mode(SaveMode.Overwrite).parquet("parquet.res");

        sc.close();

    }

}

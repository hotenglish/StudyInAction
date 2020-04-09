package com.elson.spark.sql;

import org.apache.spark.SparkConf;
import org.apache.spark.api.java.JavaSparkContext;
import org.apache.spark.sql.Dataset;
import org.apache.spark.sql.Row;
import org.apache.spark.sql.SQLContext;

public class SpecifyFormatLoadSave {

    public static void main(String args[]) {

        SparkConf conf = new SparkConf().setMaster("local").setAppName("SpecifyFormatLoadSave");

        JavaSparkContext sc = new JavaSparkContext(conf);

        SQLContext sqlContext = new SQLContext(sc);

        Dataset<Row> userDS = sqlContext.read().format("json").load("src/main/resources/people.json");

        userDS.select("name").write().format("parquet").save("src/main/resources/people.parquet");

        sc.close();

    }

}

package com.elson.spark.sql;

import org.apache.spark.sql.Dataset;
import org.apache.spark.sql.Row;
import org.apache.spark.sql.SparkSession;

public class RowNumberWindowFunction {

    public static void main(String args[]) {

        SparkSession sparkSession = SparkSession.builder().master("local").appName("HiveDataSource")
                .config("spark.sql.warehouse.dir", "/home/oracle/Servers/apache-hive-2.3.6-bin/warehouse/")
                .enableHiveSupport().getOrCreate();

        sparkSession.sql("DROP TABLE IF EXISTS sales");

        sparkSession.sql("CREATE TABLE IF NOT EXISTS sales(product STRING, category STRING,revenue BIGINT) " +
                "row format delimited fields terminated by ' ' lines terminated by '\n'");

        sparkSession.sql("LOAD DATA LOCAL INPATH '/home/oracle/learn/spark/SparkInPratice/02WorkCountJava/src/main/resources/sales.txt' INTO TABLE sales");

        Dataset<Row> top3SalesDF = sparkSession.sql("select product,category,revenue from" +
                " (select product,category,revenue,row_number() over (partition by category order by revenue desc)" +
                " rank from sales) tmp_sales where rank <= 3");


        sparkSession.sql("DROP TABLE IF EXISTS top3_sales");

        top3SalesDF.write().saveAsTable("top3_sales");

        Dataset<Row> results = sparkSession.table("top3_sales");

        results.show();

        sparkSession.stop();

    }

}

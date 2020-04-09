package com.elson.spark.sql;

import org.apache.spark.SparkConf;
import org.apache.spark.api.java.JavaRDD;
import org.apache.spark.api.java.JavaSparkContext;
import org.apache.spark.sql.Dataset;
import org.apache.spark.sql.Row;
import org.apache.spark.sql.RowFactory;
import org.apache.spark.sql.SQLContext;
import org.apache.spark.sql.types.DataTypes;
import org.apache.spark.sql.types.StructField;
import org.apache.spark.sql.types.StructType;

import java.util.ArrayList;
import java.util.List;

public class RDD2DataFrameDynamic {

    public static void main(String args[]) {

        SparkConf conf = new SparkConf().setMaster("local").setAppName("RDD2DataFrameDynamic");

        JavaSparkContext sc = new JavaSparkContext(conf);

        SQLContext sqlContext = new SQLContext(sc);

        JavaRDD<String> lines = sc.textFile("src/main/resources/student.txt");

        JavaRDD<Row> rows = lines.map(line -> {
            String[] lineSplited = line.split(",");
            return RowFactory.create(Integer.valueOf(lineSplited[0]),
                    lineSplited[1], Integer.valueOf(lineSplited[2]));
        });

        List<StructField> fields = new ArrayList<>();

        fields.add(DataTypes.createStructField("id", DataTypes.IntegerType, true));
        fields.add(DataTypes.createStructField("name", DataTypes.StringType, true));
        fields.add(DataTypes.createStructField("age", DataTypes.IntegerType, true));

        StructType schema = DataTypes.createStructType(fields);

        Dataset<Row> studentDS = sqlContext.createDataFrame(rows, schema);

        studentDS.registerTempTable("student");

        Dataset<Row> teenagerDS = sqlContext.sql("select * from student where age <= 18");

        JavaRDD<Row> teenagerRDD = teenagerDS.toJavaRDD();

        teenagerRDD.foreach(student -> System.out.println(student));

    }

}

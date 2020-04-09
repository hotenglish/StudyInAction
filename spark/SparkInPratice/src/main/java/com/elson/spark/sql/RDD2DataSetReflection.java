package com.elson.spark.sql;

import org.apache.spark.SparkConf;
import org.apache.spark.api.java.JavaRDD;
import org.apache.spark.api.java.JavaSparkContext;
import org.apache.spark.sql.Dataset;
import org.apache.spark.sql.Row;
import org.apache.spark.sql.SQLContext;

public class RDD2DataSetReflection {

    public static void main(String args[]) {

        SparkConf conf = new SparkConf().setMaster("local").setAppName("RDD2DataSetReflection");

        JavaSparkContext sc = new JavaSparkContext(conf);

        SQLContext sqlContext = new SQLContext(sc);

        JavaRDD<String> lines = sc.textFile("src/main/resources/student.txt");

        JavaRDD<Student> studentRDD = lines.map(line -> {
            String[] lineSplited = line.split(",");
            Student student = new Student();
            student.setId(Integer.valueOf(lineSplited[0]));
            student.setName(lineSplited[1]);
            student.setAge(Integer.valueOf(lineSplited[2]));
            return student;
        });

        Dataset<Row> studentDS = sqlContext.createDataFrame(studentRDD, Student.class);

        studentDS.printSchema();

        studentDS.registerTempTable("student");

        Dataset<Row> teenagerDS = sqlContext.sql("select * from student where age <= 18");

        JavaRDD<Row> teenagerRDD = teenagerDS.toJavaRDD();

        JavaRDD<Student> teenagerStudentJavaRDD = teenagerRDD.map(row -> {
            int id = row.getAs("id");
            String name = row.getAs("name");
            int age = row.getAs("age");

            Student student = new Student();
            student.setId(id);
            student.setAge(age);
            student.setName(name);

            return student;
        });

        teenagerStudentJavaRDD.foreach(student -> System.out.println(student));

    }

}

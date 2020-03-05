package com.elson.spark.ch23

import org.apache.spark.sql.Row
import org.apache.spark.sql.expressions.{MutableAggregationBuffer, UserDefinedAggregateFunction}
import org.apache.spark.sql.types._

class StringGroupCount extends UserDefinedAggregateFunction {

  override def inputSchema: StructType = {
    StructType(Array(StructField("str", StringType, true)))
  }

  override def bufferSchema: StructType = {
    StructType(Array(StructField("count", IntegerType, true)))
  }

  override def dataType: DataType = {
    IntegerType
  }

  override def deterministic: Boolean = {
    true
  }

  override def initialize(buffer: MutableAggregationBuffer): Unit = {
    buffer(0) = 0
  }

  override def update(buffer: MutableAggregationBuffer, input: Row): Unit = {
    buffer(0) = buffer.getAs[Int](0) + 1
  }

  override def merge(buffer: MutableAggregationBuffer, bufferX: Row): Unit = {
    buffer(0) = buffer.getAs[Int](0) + bufferX.getAs[Int](0)
  }

  override def evaluate(buffer: Row): Any = {
    buffer.getAs[Int](0)
  }

}

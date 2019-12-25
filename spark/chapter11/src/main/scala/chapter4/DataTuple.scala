package chapter4

class DataTuple {

  def dataTuple(tuple: Tuple2[Any, Any]) {
    tuple match {
      case (0, _) => println("0 is matched")
      case (y, 0) => println(y + "0")
      case (x, y) => println(x + ":" + y)
      case _ => print("Nothing");
    }

  }

  def data(array: Array[String]) {
    array match {
      case Array("Scala") => println("Scala ...")
      case Array(spark, hadoop, storm) => println(spark + ":" + hadoop + ":" + storm)
      case Array("Spark", _*) => println("Spark...")
      case _ => println("Nothing")
    }
  }

}

object TestDataTuple {



  def main(args: Array[String]): Unit = {
    var dataTuple=new DataTuple

    dataTuple.dataTuple(new Tuple2(0, 3))
    dataTuple.dataTuple(new Tuple2(1, 0))
    dataTuple.dataTuple(new Tuple2(2, 3))

    dataTuple.data(Array("Scala"))
    dataTuple.data(Array("Scala", "Your", "bigdata"))
    dataTuple.data(Array("Spark", "Hadoop"))
  }

}
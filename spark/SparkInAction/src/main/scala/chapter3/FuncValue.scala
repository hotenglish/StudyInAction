package chapter3

object FuncValue {

  def resultValue(num: Int, total: Int => Int): Int = {
    var hello = 0
    for (i <- 1 to num) {
      hello += total(i)
    }
    hello
  }

  def total(x: Int) = x + 2

  def functionOps(args: String*) = for (arg <- args) println(arg)

  def paraFunction(args: String) = for (arg <- args) print(arg)

  def DT(myFunc: (String) => Unit, data: String) {
    myFunc(data)
  }

  def main(args: Array[String]): Unit = {
    val result = resultValue(23, x => if (x > 0) x + 3 else 0)
    println(result)
    println(total(43))

    println(resultValue(23, x => if (x % 2 == 0) x else 0))
    println(resultValue(23, x => if (x % 2 != 0) x else 0))

    functionOps("Hadoop", "Spark", "Flunk")
    paraFunction("Spark")

    //val parameter1 = Array("Hadoop", "Spark", 23)

    //functionOps(parameter1: _*)

    val parameter2 = Array("Hadoop", "Spark", "Flink")
    functionOps(parameter2: _*)

    DT((name: String) => println(name), "Spark")

    DT(name => println(name), "Spark")

    DT(println(_), "Spark")

    DT(println, "Spark")
  }


}

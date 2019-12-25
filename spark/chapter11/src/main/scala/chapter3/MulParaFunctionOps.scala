package chapter3

object MulParaFunctionOps {

  def mulParaFunctionOps(func: (Int, Int) => Int, array: Array[Int], item: Int): Int = {
    var tmp = item
    array.foreach { x => tmp = func(tmp, x) }
    tmp
  }

  def func(x: Int, y: Int) = x + y

  def main(args: Array[String]): Unit = {
    var arr = Array(12, 34, 5, 7, 76)
    var sum = mulParaFunctionOps((tmp, x) => tmp + x, arr, 0)
    println(sum)
  }

}

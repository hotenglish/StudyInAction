package chapter3

object OccupyChar {

  def main(args: Array[String]): Unit = {
    List(1, 2, 3, 4).map((x: Int) => x + 2)
    // it will caused error when uncomment the blow code
    //List(1, 2, 4, 5).map(x: Int => x + 2)

    List(1, 2, 4, 5).map(x => x + 2)
    List(1, 2, 4, 5).map(_ + 2).foreach(println)

    var functionValue = (x: Int) => x + 10
    var aa = functionValue(3)
    println(aa)

    val array = Array(1, 2, 3, 4, 5, 6)
    val out1 = array.filter(item => item > 2).foreach(println(_))
    val out2 = array.map(_ + 2).filter(_ > 2).foreach(println)

    println((_: Int) + (_: String))

    def functionOps = (_: Int) + (_: String)

    println(functionOps)
    println(functionOps(1, "Spark"))
    var partial1 = functionOps(1, "Spark")
    val partial2 = functionOps(1, _: String)
    println(partial2)

    def helloScala(x: Int, y: Int, z: Int) = x + y + z

    println(helloScala(_: Int, 3, 5))

    println(helloScala(4, 5, _: Int))
    println(helloScala _)
    var hello = helloScala _
    println(hello(2, 45, _: Int))
    println(hello(2, 45, 3))
  }

}

package chapter3

object Closer {

  def main(args: Array[String]) {

    /*   var more = 1

       (x: Int) =>x + more
         (x: Int) =>x + 1*/

    def hiScala(humen: String) = (Lod: String) => println(Lod + "," + humen)

    val helloScala = hiScala("yesu")

    helloScala("Lod")

    def fun(a: Int, b: Long) = a + b

    def fun_1(a: Int) = (b: Long) => a - b

    println(fun_1(54)(34))

    def func_Curring(x: String)(y: String) {
      println(x + "+" + y)
    }

    func_Curring("Spark")("Hadoop")

    def functionCurring1(a: Int)(b: Int)(c: Int) = println(a * b * c)

    def functionCurring2(a: Int)(b: Int)(c: Int) {
      println(a * b * c)
    }

    functionCurring1(2)(3)(4)

    functionCurring2(2)(3)(4)

    def func1(x: Int)(y: Int, yy: Int)(z: Int) = x + y + z

    println(func1 _)

    def func2(x: Int)(y: Int, yy: String)(z: Int) = x + y + z + yy

    println(func2(1)(2, "Spark")(4))


    val myPartialFunc1: PartialFunction[Int, String] = {
      case x if (x > 0) => "Nice!"
      case _ => "The other thing"
    }
    println(myPartialFunc1(4))
    println(myPartialFunc1(-34))

    val myPartialFunc2: PartialFunction[Int, String] = {
      case x if (x > 0) => "Nice!"
    }
    println(myPartialFunc2(2))

    val myPartialFunc3: (Int => String) = myPartialFunc2.orElse { case _ => "bad!" }

    println(myPartialFunc3(-2))

    val people: PartialFunction[Int, String] = {
      case age if age <= 20 => "Child "
      case age if age <= 60 && age > 20 => "main"
    }

    println(people.isDefinedAt(15))

    println(people.isDefinedAt(61))

    val oldman: PartialFunction[Int, String] = people.orElse { case age if age > 60 => "oldman" }
    println(oldman(61))
  }

}
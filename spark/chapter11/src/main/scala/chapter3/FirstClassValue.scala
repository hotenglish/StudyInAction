package chapter3

object FirstClassValue {

  def main(args: Array[String]): Unit = {
    var myScala = (A: Long) => A + 1
    println(myScala(0))
    var myFirstClassValue = (A: Int) => A + 99
    println(myFirstClassValue(1))
    myFirstClassValue = (A: Int) => {
      println("Hello Scala")
      println("Hello Scala")
      A - 10
    }
    myFirstClassValue(2)
  }

}

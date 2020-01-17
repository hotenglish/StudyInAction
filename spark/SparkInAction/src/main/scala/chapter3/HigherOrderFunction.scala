package chapter3

object HigherOrderFunction {

  def main(args: Array[String]): Unit = {
    val hiScala = (name: String) => println(name)

    def helloScala(myFunction: (String) => Unit, context: String) {
      myFunction(context)
    }

    helloScala(hiScala, "Spark is Wonderful!")

    def helloSpark(name: String) = (name: String) => println(name)

    val spark = helloSpark("Scala")
    spark("Good")
  }

}

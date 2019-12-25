package chapter3

class ConstructorAux {

  private var name = ""

  private var age = 0

  def this(name: String) {
    this()
    this.name = name
    println(name)
  }


  def this(name: String, age: Int) {
    this(name)
    this.name = name
    this.age = age
    println(name + ":" + age)
  }

}

object ConstructorAux {

  def main(args: Array[String]) {
    val bigDate = new ConstructorAux
    val hadoop = new ConstructorAux("Hadoop")
    val spark = new ConstructorAux("Spark", 4)
  }

}
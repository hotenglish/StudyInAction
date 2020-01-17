package chapter2

class PersonConstructorOverride(name: String) {
  println(this.name)

  def this(name: String, age: Int) {
    this(name)
    println(name + " " + age)
  }

  def this(name: String, age: Int, sex: String) {
    this(name, age)
    println(name + ":" + sex)
  }

}

object PersonConstructorOverride {

  def main(ags: Array[String]): Unit = {
    val personConstructorOverride = new PersonConstructorOverride("lai",38, "male")
  }

}

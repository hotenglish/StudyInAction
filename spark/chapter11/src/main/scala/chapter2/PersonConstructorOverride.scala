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

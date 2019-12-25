package chapter2

class PersonObjectField {
  var name: String = "zhangsan"
  var age = 30
  val sex = "female"

  def sayHi = println("Hi!!!")

  def increase(age: Int): Int = this.age + age
}

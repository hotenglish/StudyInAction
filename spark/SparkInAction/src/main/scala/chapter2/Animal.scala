package chapter2

abstract class Animal {

  def eat {
    println("Eat food!")
  }

  def run

}

class Cat extends Animal {
  override def eat: Unit = {
    println("Eat mouse!!!")
  }

  override def run: Unit = {
    println("Running...")
  }
}

class Dog extends Animal {
  override def run: Unit = {
    println("Dog is running...")
  }
}

object Animal {
  def main(args: Array[String]) {
    val c = new Cat
    c.eat
    c.run
    val d = new Dog
    d.eat
    d.run
  }
}
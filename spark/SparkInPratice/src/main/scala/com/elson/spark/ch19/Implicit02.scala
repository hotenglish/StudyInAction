package com.elson.spark.ch19

class Car(val name: String)

class SuperMan(name: String) {
  def emitlaser() = println("emit a pingpang ball !!!")
}

object Implicit02 {

  implicit def card2Superman(car: Car): SuperMan = {
    new SuperMan(car.name)
  }

  def main(array: Array[String]): Unit = {
    var car = new Car("qingtianzhu");
    car.emitlaser();
  }

}

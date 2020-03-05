package com.elson.spark.ch19

class SpecialPerson(name: String)

class Older(val name: String)

class Child(val name: String)

class Teacher(val name: String)

object Implicit01 {

  implicit def object2SpecialPerson(obj: Object): SpecialPerson = {
    if (obj.getClass == classOf[Older]) {
      val orlder = obj.asInstanceOf[Older]
      new SpecialPerson(orlder.name)
    } else if (obj.getClass == classOf[Child]) {
      val child = obj.asInstanceOf[Child]
      new SpecialPerson(child.name)
    } else {
      //println("Nil")
      Nil
    }
  }

  var sumTickits = 0

  def buySpecialTicker(specialPerson: SpecialPerson): Unit = {
    sumTickits += 1
    println(sumTickits)
  }

  def main(array: Array[String]): Unit = {
    val orlder = new Older("laowang")
    buySpecialTicker(orlder)
    val child = new Child("xiaoming")
    buySpecialTicker(child)
    val teacher = new Teacher("yasaka")
    buySpecialTicker(teacher)
  }

}

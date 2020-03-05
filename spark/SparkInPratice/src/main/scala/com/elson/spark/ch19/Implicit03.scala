package com.elson.spark.ch19

class SignPen() {
  def write(name: String) = println(name)
}

object ImplicitContext {
  implicit val signPen = new SignPen()
}

object Implicit03 {

  def signForExam(name: String)(implicit signPen: SignPen): Unit = {
    signPen.write(name)
  }

  def signForRegister(name: String)(implicit signPen: SignPen): Unit = {
    signPen.write(name)
  }

  def main(args: Array[String]): Unit = {
    import ImplicitContext._
    signForExam("yasaka")
    signForRegister("yasaka")
  }

}

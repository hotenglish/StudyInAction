package chapter2

class AttributeOverride {

  val name: String = "Jack"
  final val sex: String = "male"

  final def sayHi = {
    println("Hi Scala...")
  }
}

class SubAttribute1 extends AttributeOverride {
  override val name: String = "Michael"
  // it will be cause compile with error when uncomment below code
  //override val sex: String = "female"
}

class SubAttribute2 extends AttributeOverride {
  override val name: String = "Michael"
  // it will be cause compile with error when uncomment below code
  /*  override def sayHi = {
      println("Hi SubAttribute1...")
    }*/

}

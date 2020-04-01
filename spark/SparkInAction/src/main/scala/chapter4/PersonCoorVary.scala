package chapter4

trait PersonCoorVary1[+T] {
  def eat
}

trait PersonCoorVary2[-T] {
  def eat
}

object CoorVaryTest {
  val children1 = new PersonCoorVary1[String] {
    override def eat = {
      println("children1 have lunch...")
    }
  }

  val father2 = new PersonCoorVary2[Any] {
    override def eat = {
      println("father2 have lunch...")
    }
  }

  def main(args: Array[String]) {
    var father1: PersonCoorVary1[Any] = children1
    var action1: Any = father1.eat

    var children2: PersonCoorVary2[String] = father2
    val action2: Any = children2.eat
  }

}

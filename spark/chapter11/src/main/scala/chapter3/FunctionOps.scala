package chapter3

object FunctionOps {

  class AA {

  }

  class BB {
    def A {
      def B {

      }
    }
  }

  def B {
    println("Local function!")
  }

}


class BB {

  def A(x: Int, y: Int): Unit = {
    def B: Unit = {
      println(x * y)
      var b = new BB
      val a = b.A(3, 6)
      println(a)
    }
  }

  def B: Unit = {
    println("Local function!")
  }

}

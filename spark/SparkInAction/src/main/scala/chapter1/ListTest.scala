package chapter1

object ListTest {

  def main(args: Array[String]) {

    var fruit: List[String] = List("apples", "oranges", "pears")

    var nums: List[Int] = List(1, 2, 3, 4)

    var empty: List[Nothing] = List()

    var dim: List[List[Int]] = List(List(1, 0, 0), List(1, 0, 0), List(0, 0, 1))

    val fruit2 = "apples" :: (("oranges") :: ("pears" :: Nil))

    val nums2 = 1 :: (2 :: (3 :: (4 :: Nil)))

    val empty2 = Nil

    val dim2 = (1 :: (0 :: (0 :: Nil))) :: (0 :: (1 :: (0 :: Nil))) :: (0 :: (0 :: (1 :: Nil))) :: Nil

    fruit.foreach(println)

    for (i <- nums) {
      print(i + " ")
    }

    println()

    val t = new Test

    t.ops
  }

}

class Test {

  def ops: Unit = {

    val fruit = "apples" :: (("oranges") :: ("pears" :: Nil))

    val nums = Nil

    println("Head of fruit:" + fruit.head)

    println("Tail of fruit:" + fruit.tail)

    println("Check if fruit is empty:" + fruit.isEmpty)

    println("Check if fruit is empty:" + nums.isEmpty)

  }

}

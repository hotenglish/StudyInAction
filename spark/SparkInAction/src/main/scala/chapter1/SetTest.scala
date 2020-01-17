package chapter1

object SetTest {

  def main(args: Array[String]): Unit = {
    var s1: Set[Int] = Set()
    var s2: Set[Int] = Set(1, 3, 5, 7)
    var s3: scala.collection.immutable.Set[Int] = Set(1, 3, 5, 7)
    val str: Set[String] = Set("Scala", "Spark", "Hadoop")
    val st = new SetClass
    st.setMethodTest
  }


}

class SetClass {

  def setMethodTest: Unit = {
    val book = Set("Scala", "Spark", "Hadoop")
    val nums: Set[Int] = Set()

    println("Head of book:" + book.head)
    println("Tail of book:" + book.tail)
    println("Check if book is empty:" + book.isEmpty)
    println("Check if nums is empty:" + nums.isEmpty)
  }

}
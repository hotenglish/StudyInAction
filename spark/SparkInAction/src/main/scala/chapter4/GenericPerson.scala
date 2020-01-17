package chapter4

class GenericPerson[T](val content: T) {

  def getContent(id: T) = id + "_" + content

  def getElem[T](list: List[T]) = list(list.length - 1)

}

object TestGenericPerson {
  def main(args: Array[String]) {
    val p = new GenericPerson[String]("Spark")
    println(p.getContent("Scala"))
    println(p.getElem(List("Spark", "Storm", "Scalar")))
    println(p.getElem(List(1, 2, 3, 4, 5)))
  }
}
package chapter2

object OptionTest {

  def main(args: Array[String]) {
    val capitals = Map("CHINA" -> "BEIJING", "FRANCE" -> "PARIS")
    println("capital.get(\"CHINA\"):" + capitals.get("CHINA"))
    println("capital.get(\"India\"):" + capitals.get("India"))
    var arr1 = Array(1, 2, 3)
    val arr2 = Array.apply(1, 2, 3)
  }

}

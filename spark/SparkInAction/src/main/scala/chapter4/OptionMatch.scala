package chapter4

object OptionMatch {

  def main(args: Array[String]): Unit = {
    val capitals = Map("FRANCE" -> "PARIS", "KOREA" -> "SEOUL")
    println("show(capitals.get(\"KOREA\"))"+ show(capitals.get("KOREA")))
    println("show(capitals.get(\"INDIA\"))"+ show(capitals.get("INDIA")))
  }

  def show(x: Option[String]) = x match {
    case Some(s) => s
    case None => "Nothing"
  }

}

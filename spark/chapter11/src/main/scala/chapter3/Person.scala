package chapter3

abstract class Animal

class Person(head: Int, hand: Int, foot: Int, name: String) extends Animal {

  require(head > 0 && head < 2)
  require(hand >= 0 && hand < 3, "error input of hand, please input hand retring")
  require(foot >= 0 && foot < 3, foot)

  def Working(fangFa: String) = {
    val total = head + hand + foot

    println(name + " is a " + fangFa + " and working by \n\t" + head + " head to think and \n\t" +
      hand + " hands to coorperate something and \n\t" + foot + " foots to walk!")

    println(name + " are use total Organ." + total)

    total
  }

}

object FunctionObject {
  def main(args: Array[String]): Unit = {
    val perple = new Person(1, 2, 2, "Tom")
    perple.Working("Programmer")
  }
}
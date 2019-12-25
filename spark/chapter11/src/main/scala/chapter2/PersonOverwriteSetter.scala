package chapter2

class PersonOverwriteSetter {

  private var myName = "Flink"

  def name = this.myName

  def name_=(newName: String) {
    myName = newName
    println("Hi " + myName)
  }

}

object PersonOverwriteSetterTest {
  def main(args: Array[String]) {
    val personOverwriteSetter = new PersonOverwriteSetter
    personOverwriteSetter.name = "Scala"
  }
}
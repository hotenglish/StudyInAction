package chapter2

class PersonSelfDefineSetter {

  private var myName = "Flink"

  def name = this.myName

  def update(newName: String) {
    myName = newName
    println("Hi " + myName)
  }

}

object PersonSelfDefineSetterTest {
  def main(args: Array[String]) {
    val PersonSelfDefineSetter = new PersonSelfDefineSetter
    //personOverwriteSetter.name = "Scala"
    PersonSelfDefineSetter.update("Scala")
  }
}

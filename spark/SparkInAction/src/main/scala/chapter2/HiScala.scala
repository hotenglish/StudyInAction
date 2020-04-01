package chapter2

class HiScala {

  private val name:String="zhangsan"

  def sayName(){
      print(name)
  }

  def getName=name

}

object AnimalTest{
  def main(args:Array[String]){
    val hiScala=new HiScala
    hiScala.sayName
    hiScala.getName
  }
}

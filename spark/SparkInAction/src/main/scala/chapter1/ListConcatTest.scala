package chapter1

object ListConcatTest {

  def main(args:Array[String]): Unit ={
    concatTest
  }

  def concatTest = {
    val fruit1 = "apples" :: (("oranges") :: ("pears" :: Nil))
    val fruit2 = "mangoes" :: ("bananas" :: Nil)
    var fruit = fruit1 ::: fruit2
    println("fruit1:::fruit2:" + fruit)

    fruit = fruit1.:::(fruit2)
    println("fruit1:::fruit2:" + fruit)

    fruit = List.concat(fruit1, fruit2)
    println("List.concat(fruit1,fruit2):" + fruit)
  }

}



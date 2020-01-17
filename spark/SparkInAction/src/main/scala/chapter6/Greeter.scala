package chapter6

object Greeter {
  case object Greet
  case object Done
}

import akka.actor.{Actor, Props}

class Greeter extends Actor {
  override def receive = {
    case Greeter.Greet => println("Hello World!")
      sender() ! Greeter.Done
  }
}

class HelloWorld extends Actor {

  override def preStart(): Unit = {
    val greeter = context.actorOf(Props[Greeter], "greeter")
    greeter ! Greeter.Greet
  }

  def receive = {
    case Greeter.Done => context.stop(self); println("Handle message finished...")
  }

}

object MainTest {
  def main(args: Array[String]): Unit = {
    akka.Main.main(Array(classOf[HelloWorld].getName))
  }
}
package chapter6

import akka.actor.{Actor, ActorLogging, ActorSystem, Props}

object MyActor1 {

  case class Greeting(from: String)

  case object GoodBye

  def main(args: Array[String]): Unit = {
    val system = ActorSystem("test")
    val myActor = system.actorOf(Props[MyActor1], "MyActor1")
    myActor ! Greeting("Hello")
    myActor ! GoodBye
    myActor ! "Good Boy!"
  }

}

class MyActor1 extends Actor with ActorLogging {

  import chapter6.MyActor1._

  override def receive = {
    case Greeting(greeter) => println(s"was greeted by $greeter.")
    case GoodBye => println("Someone said goodbye to me")
    case _ => println("Something else...")
  }

}

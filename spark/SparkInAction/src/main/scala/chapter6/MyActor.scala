package chapter6

import akka.actor.{Actor, ActorSystem, Props}

class MyActor extends Actor {

  override def receive = {
    case "test" => println("received test")
    case _ => println("received unknown message")
  }

}

object MyActorTest {

  def main(args: Array[String]): Unit = {
    var system = ActorSystem("MyActor")
    var myActor = system.actorOf(Props[MyActor], "MyActor")
    myActor ! "test"
  }

}
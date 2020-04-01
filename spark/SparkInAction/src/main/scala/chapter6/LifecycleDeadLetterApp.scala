package chapter6

import akka.actor.{Actor, ActorSystem, DeadLetter, Props}

object LifecycleDeadLetterApp {

  def main(args: Array[String]): Unit = {
    val actorSystem = ActorSystem("LifecycleActorSystem")
    val lifecycleLoggingSecondActor = actorSystem.actorOf(Props[BasicLifecycleLoggingSecondActor], "lifecycleActor")
    val deadLetterListener = actorSystem.actorOf(Props[MyCustomerDeadLetterListener])
    actorSystem.eventStream.subscribe(deadLetterListener, classOf[DeadLetter])
    lifecycleLoggingSecondActor ! "hello"
    lifecycleLoggingSecondActor ! "stop"
    lifecycleLoggingSecondActor ! "hello"
  }

}

class MyCustomerDeadLetterListener extends Actor {
  override def receive = {
    case deadLetter: DeadLetter => println(s"FROM CUSTOMER $deadLetter")
  }
}

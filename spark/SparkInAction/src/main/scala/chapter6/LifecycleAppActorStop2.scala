package chapter6

import akka.actor.{ActorSystem, Kill, PoisonPill, Props}

object LifecycleAppActorStop2 {

  def main(args: Array[String]): Unit = {
    val actorSystem = ActorSystem("LifecycleActorSystem")
    val lifecycleActor = actorSystem.actorOf(Props[BasicLifecycleLoggingSecondActor], "lifecycleActor")
    lifecycleActor ! "hello"
    //lifecycleActor ! "stop"
    //lifecycleActor ! PoisonPill
    lifecycleActor ! Kill
  }

}
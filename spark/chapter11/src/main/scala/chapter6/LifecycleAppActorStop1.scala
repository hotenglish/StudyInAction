package chapter6

import akka.actor.{ActorSystem, Props}

object LifecycleAppActorStop1 {

  def main(args: Array[String]): Unit = {
    val actorSystem = ActorSystem("LifecycleActorSystem")
    val lifecycleActor = actorSystem.actorOf(Props[BasicLifecycleLoggingActor], "lifecycleActor")
    lifecycleActor ! "Hello"
    Thread.sleep(2000)
    actorSystem.stop(lifecycleActor)
  }

}

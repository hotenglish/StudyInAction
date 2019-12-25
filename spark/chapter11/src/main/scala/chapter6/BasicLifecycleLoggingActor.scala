package chapter6

import akka.actor.{Actor, ActorLogging, ActorSystem, Props}
import akka.event.LoggingReceive

class BasicLifecycleLoggingActor extends Actor with ActorLogging {

  log.info("Inside BasicLifecycleLoggingActor Constructor")
  log.info(context.self.toString())

  override def preStart() = {
    log.info("BasicLifecycleLoggingActor preStart method is invoked ...")
  }

  override def receive = LoggingReceive {
    case "Hello" => log.info("hello")
  }

  override def postStop() = {
    log.info("BasicLifecycleLoggingActor postStop method is invoked...")
  }
}

object LifecycleApp extends App {

  override def main(args: Array[String]) {
    val actorSystem = ActorSystem("LifecycleActorSystem")
    val lifecycleActor = actorSystem.actorOf(Props[BasicLifecycleLoggingActor], "lifecycleActor")
    lifecycleActor ! "hello"
    Thread.sleep(2000)
    actorSystem.shutdown()
  }

}
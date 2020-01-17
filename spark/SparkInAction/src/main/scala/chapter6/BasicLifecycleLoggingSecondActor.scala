package chapter6

import akka.actor.{Actor, ActorLogging}
import akka.event.LoggingReceive

class BasicLifecycleLoggingSecondActor extends Actor with ActorLogging {

  log.info("Inside BasicLifecycleLoggingSecondActor Constructor")
  log.info(context.self.toString())

  override def preStart() = {
    log.info("BasicLifecycleLoggingSecondActor preStart method is invoked ...")
  }

  override def receive = LoggingReceive {
    case "hello" => log.info("hello")
    case "stop" => context.stop(self)
  }

  override def postStop() = {
    log.info("BasicLifecycleLoggingSecondActor postStop method is invoked...")
  }
}

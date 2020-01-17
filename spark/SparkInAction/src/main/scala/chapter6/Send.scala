package chapter6

import akka.actor.{Actor, ActorRef, ActorSystem, Props}

case object SendMessage

case object ReceMessage

case object StartMessage

case object StopMessage

class Send(rece: ActorRef) extends Actor {

  var count = 0

  def incrementAndPrint: Unit = {
    count += 1; println("Send")
  }

  override def receive = {
    case StartMessage => incrementAndPrint
      rece ! SendMessage
    case ReceMessage =>
      if (count > 9) {
        sender ! StopMessage
        println("Send stopped")
        context.stop(self)
      } else {
        incrementAndPrint
        //println("count="+count)
        sender ! SendMessage
      }
  }

}

class Rece extends Actor {

  override def receive = {
    case SendMessage =>
      println(" Rece")
      sender ! ReceMessage
    case StopMessage =>
      println("Rece stopped")
      context.stop(self)
      context.system.shutdown()
  }

}

object SendReceTest extends App {

  val system = ActorSystem("SendReceSystem")
  var rece = system.actorOf(Props[Rece], name = "Rece")
  var send = system.actorOf(Props(new Send(rece)), name = "Send")
  send ! StartMessage

}


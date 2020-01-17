package chapter6

import akka.actor.{Actor, Props}


object AnonymousActor {

/*  def receive = {

    case m: DoIt => context.actorOf(Props(new Actor {
      override def receive = {
        case DoIt(msg) =>
          val replyMsg = doSomeDangerousWork(msg)
          sender ! replyMsg
          context.stop(self)
      }

      def doSomeDangerousWork(msg: ImmutableMessage): String = {
        "Done"
      }
    }))forward m

  }*/


}

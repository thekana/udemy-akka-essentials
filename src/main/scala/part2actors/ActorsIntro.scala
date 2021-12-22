package part2actors

import akka.actor.{Actor, ActorSystem, Props}

object ActorsIntro extends App {
  val actorSystem = ActorSystem("firstActorSystem")
  println(actorSystem.name)

  class WordCountActor extends Actor {

    var totalWords = 0

    override def receive: Receive = {
      case message: String =>
        println(s"received $message")
        message.split(" ")
      case msg => println(s"${msg.toString}")
    }
  }

  val wordCounter = actorSystem.actorOf(Props[WordCountActor], "wordCounter")

  wordCounter ! "WOAH HEY COOL"

  // best practice to create prop

  object Person {
    def props(name: String): Props = Props(new Person(name))
  }

  class Person(name: String) extends Actor {
    override def receive: Receive = {
      case "hi" => println(s"hi $name")
      case _ => Unit
    }
  }

  val person = actorSystem.actorOf(Person.props("bob"))

  person ! "hi"

  actorSystem.terminate()
}

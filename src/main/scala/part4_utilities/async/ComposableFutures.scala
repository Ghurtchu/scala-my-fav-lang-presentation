package part4_utilities.async

import scala.concurrent.Future
import scala.concurrent.ExecutionContext.Implicits.global

object ComposableFutures {

  // non-blocking asynchronous task:
  // 1) fetch user
  // 2) send email
  // 3) store event into event-store

  final case class User(id: String, name: String)

  object UserService:

    def getUserById(id: String): Future[Option[User]] = {
      println("Returning user")
      Future(Some(User(id, "David")))
    }

  final case class EmailResponse(value: String)

  object EmailService:

    def sendEmailToUser(userId: String, email: String): Future[EmailResponse] = {
      println(s"Sending email to user with id $userId")
      for (_ <- 1 to 5) do {
        println("...")
        Thread sleep 250
      }

      println(s"Email: $email has been sent successfully")
      Future(EmailResponse("Success"))
    }

  final case class Event(id: String, name: String)
  final case class AppendOperation(value: String)

  object EventService:
    def store(event: Event): Future[AppendOperation] = Future {
      println("appending event...")
      Thread sleep 500

      println("event has been appended successfully")
      AppendOperation("Success")
    }

  def main(args: Array[String]): Unit = {

    val userId = "10"

    val composed = for {
      user <- UserService.getUserById(userId)
      _    <- user.fold(Future.successful(())) { user =>
        EmailService.sendEmailToUser(userId, s"Hey ${user.name}")
      }
      _    <- EventService.store(Event(userId, "notification.sent"))
    } yield ()

    println("main thread continued, non-blocking!")

    Thread sleep 5000
  }

}

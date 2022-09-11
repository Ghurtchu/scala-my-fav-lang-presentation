package part4_utilities.implicits

import scala.concurrent.Future
import scala.util.{Failure, Success}
import scala.concurrent.ExecutionContext.Implicits.global

object Futures {

  def main(args: Array[String]): Unit = {

    // future is not lazy, it's eager computation
    // for ultimate laziness you should go to functional effect systems such as ZIO, Cats and etc..
    val future = Future {
      Thread sleep 500 // sleep for half a sec
      println(Thread.currentThread().getName)
      println("Hello from Future!")
    }

    future.onComplete {
      case Failure(exception) => println("Ugh..")
      case Success(value) => println("Bye from Present!")
    }

    Thread sleep 1000

    println("main thread finished")
  }

}

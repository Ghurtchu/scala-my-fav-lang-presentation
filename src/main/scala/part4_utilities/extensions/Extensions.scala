package part4_utilities.extensions

import scala.collection.concurrent.TrieMap

object Extensions {

  case class Machine(speed: Int)

  def main(args: Array[String]): Unit = {

    "all work and no play makes Jack a dull boy"
      .surroundWith('*')
      .capitalizeFirst
      .putInQuotes
      .display

  }

  trait ThirdPartyInterface {
    def call: Unit
  }

  // you can't extend it by traditional OOP techniques
  // but Scala 3 allows you to use extension methods to do it
  final class ThirdPartyService extends ThirdPartyInterface {
    override def call: Unit = {
      // some implementation details
    }
  }
  TrieMap()

  extension(thirdPartyService: ThirdPartyService)
    def newMethod: Unit = println("calling from extension")

}


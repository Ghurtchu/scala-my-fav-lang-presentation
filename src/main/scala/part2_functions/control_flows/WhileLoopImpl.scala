package part2_functions.control_flows

import scala.annotation.tailrec

object WhileLoopImpl:

  @main def run =
    var num = 0

    runWhile(num < 10) {
      program
      num += 1
    }

    def program: Unit = {
      println("Running program...")
      "...".showN(3)
      println(scala.util.Random.nextInt(10))
      "...".showN(3)
      println("Finished")
    }


  // both are by name parameters, which are unevaluated at call site
  // they are evaluated when they're first used inside the method block
  @tailrec
  def runWhile[A](predicate: => Boolean)(program: => A): Unit =
    if !predicate then ()
    else
      program
      runWhile(predicate)(program)

  extension (str: String)
    def showN(n: Int): Unit = (0 to n).foreach(_ => {
      Thread sleep 125
      println(str)
    })
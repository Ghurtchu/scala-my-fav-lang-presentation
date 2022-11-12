package part2_functions.recursion

import java.util.concurrent.Callable
import scala.annotation.tailrec
import scala.collection.mutable.ListBuffer


// incrementally make the function more customized (HOF-s, composition, default method parameters)

object Recursion {

  def main(args: Array[String]): Unit = {

    println {
      generate(
        n = 100)
    }
  }

  def rand: Int = scala.util.Random.nextInt(100)

  // 3) generate n amount of random numbers filtered by some logic with the exit criteria
  def generate(n: Int, f: Int => Boolean = _ => true, exit: Int => Boolean = _ => false): List[Int] = {

    @tailrec
    def loop(amount: Int, acc: List[Int]): List[Int] = {
      val number = rand
      if amount == 0 then acc
      else if exit(number) then {
        println("Exit criteria happened")
        println(s"N=$number")

        acc
      }
      else if f(number) then loop(amount - 1, number :: acc)
      else loop(amount - 1, acc)
    }

    loop(n, Nil)
  }
  
}

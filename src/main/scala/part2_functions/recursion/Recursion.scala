package part2_functions.recursion

import java.util.concurrent.Callable
import scala.annotation.tailrec
import scala.collection.mutable.ListBuffer


// incrementally make the function more customized (HOF-s, composition)

object Recursion {

  def main(args: Array[String]): Unit = {

  }

  def rand: Int = scala.util.Random.nextInt(100)

//   1) generate n amount of random numbers
  def generate(n: Int): List[Int] = ???

  // 2) generate n amount of random numbers filtered by some logic
  def generate(n: Int, f: Int => Boolean): List[Int] = ???

  // 3) generate n amount of random numbers filtered by some logic with the exit criteria
  def generate(n: Int, f: Int => Boolean = _ => true, exit: Int => Boolean = _ => false): List[Int] = ???


}

package part1_data_modeling.sum_types

object TryImpl {

  // Attempt
  // implement the "enough" version of Try data structure of scala stdlib

  // laziness (by-name params), generics, pattern matching, sum types
  
  def main(args: Array[String]): Unit = 

    println("-" * 50)

    println("-" * 50)


  enum Attempt[+A]:

    self =>

    case Success(value: A)
    case Failure(t: Throwable)

    def get: A = ???

    def map[B](f: A => B): Attempt[B] = ???

    def flatMap[B](f: A => Attempt[B]): Attempt[B] = ???

    def fold[B](onFailure: Throwable => B)(onSuccess: A => B): B = ???

    def filter(f: A => Boolean): Attempt[A] = ???

  // companion objects store "static"-like methods along with constructors & shared data
  object Attempt:
    def apply[A](a: => A): Attempt[A] = ???
  
}

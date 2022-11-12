package part2_functions.patterns

import MonoidInstances.intMonoid
import MonoidInstances.strMonoid

// A good example of what object-functional design means

// Abstract over classes which have the ability to combine two values of the same type
trait SemiGroup[A]:
  def combine(x: A, y: A): A

// Abstract over classes which have the type of SemiGroup and also the ability to provide zero value
trait Monoid[A] extends SemiGroup[A]:
  def zero: A

object MonoidInstances {
  // sensible default int impl
  given intMonoid: Monoid[Int] = new Monoid[Int] {
    override def combine(x: Int, y: Int): Int = x + y
    override def zero: Int = 0
  }

  // sensible default string impl
  given strMonoid: Monoid[String] = new Monoid[String] {
    override def zero: String = ""
    override def combine(x: String, y: String): String = x concat y
  }
}

// for any given instance of type A we're creating two methods, zero and combine
extension[A](a: A)
  def zero(using monoid: Monoid[A]): A = monoid.zero
  def <±>(b: A)(using monoid: Monoid[A]): A = monoid.combine(a, b)

import SemiGroup.Point
given pointMonoid: Monoid[Point] = new Monoid[Point] {
  override def zero: Point = Point(0, 0)
  override def combine(x: Point, y: Point): Point = Point(x.x + y.x, x.y + y.y)
}

object SemiGroup:

  def main(args: Array[String]): Unit =

    println(combine(List(1, 2, 3)))
    println(combine(List(Point(1, 1), Point(2, 2), Point(3, 3))))
    println(combine(List("a", "b", "c")))
  
  final case class Point(x: Int, y: Int)

  // lets us to write a generic function which can combine any type for which the monoid instance is present in implicit scope
  def combine[A](list: List[A])(using monoid: Monoid[A]): A = list.foldLeft(monoid.zero)(monoid.combine)




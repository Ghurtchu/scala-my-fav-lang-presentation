package part2_functions.patterns

import javax.management.monitor.StringMonitor

// A good example of what object-functional design means

// Abstract over classes which have the ability to combine two values of the same type
trait SemiGroup[A]:
  def combine(x: A, y: A): A

// Abstract over classes which have the type of SemiGroup and also the ability to provide zero value
trait Monoid[A] extends SemiGroup[A]:
  def zero: A

// sensible default int impl
final class IntegerMonoid extends Monoid[Int]:
  override def zero: Int = ???
  override def combine(x: Int, y: Int): Int = ???

// sensible default string impl
final class StringMonoid extends Monoid[String]:
  override def zero: String = ???
  override def combine(x: String, y: String): String = ???

// for any given instance of type A we're creating two methods, zero and combine
extension[A](a: A)
  def zero(using monoid: Monoid[A]): A = monoid.zero
  def combine(b: A)(using monoid: Monoid[A]): A = monoid.combine(a, b)

given intMonoid: Monoid[Int] = IntegerMonoid()
given stringMonoid: Monoid[String] = StringMonoid()

object SemiGroup:

  def main(args: Array[String]): Unit = 
    println(combine(List(1, 2, 3)))
  
  def combine[A](list: List[A])(using monoid: Monoid[A]): A = ???




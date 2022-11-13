package part1_data_modeling.sum_types
object TryImpl {

  // Attempt
  // implement the "enough" version of Try data structure of scala stdlib

  // laziness (by-name params), generics, pattern matching, sum types
  
  def main(args: Array[String]): Unit = {
    println("-" * 50)

    import scala.util.*

//    val chainedAttemptUgly: Attempt[Int] =
//      Attempt(scala.util.Random.nextInt(100)).flatMap { r1 =>
//        Attempt(r1 / 5).flatMap { r2 =>
//          Attempt(Math.pow(r2, 3).toInt).flatMap { r3 =>
//            Attempt(r3 / 0) // Failure(arithmetic exception)
//          }
//        }
//      }

    val chainedAttemptBeautiful: Attempt[Int] = for {
      r1 <- Attempt(scala.util.Random.nextInt(100)) // flatMap Success(5)
      r2 <- Attempt(r1 / 0) // flatMap Failure(ArithmeticException)
      r3 <- Attempt(Math.pow(r2, 3).toInt) // flatMap flatMap Failure(ArithmeticException)
      r4 <- Attempt(r3 / 5) // map flatMap Failure(ArithmeticException)
    } yield r4 // Failure(ArithmeticException)

    println(chainedAttemptBeautiful.fold[String](_ => "0")(_.toString))

    println("-" * 50)
  }

      // +A means "Covariant"
      // if A <: B then Attempt[A] <: Attempt[B]
    enum Attempt[+A] {

        import Attempt.*

        // Either of these two cases
        case Success(value: A)
        case Failure(t: Throwable)

        def get: A = this match
          case Success(v) => v
          case Failure(t) => throw new UnsupportedOperationException("???")

        def map[B](f: A => B): Attempt[B] = this match
          case Success(v) => Success(f(v))
          case Failure(t) => Failure(t)

        def flatMap[B](f: A => Attempt[B]): Attempt[B] = this match
          case Success(v) => f(v)
          case Failure(t) => Failure(t)

        def fold[B](onFailure: Throwable => B)(onSuccess: A => B): B = this match
          case Success(v) => onSuccess(v)
          case Failure(t) => onFailure(t)
      }


    // companion objects store "static"-like methods along with constructors & shared data
    object Attempt {
      // by name parameter : => A, delayed
      def apply[A](a: => A): Attempt[A] = try Success(a) catch case e: Throwable => Failure(e)
    }
  
}

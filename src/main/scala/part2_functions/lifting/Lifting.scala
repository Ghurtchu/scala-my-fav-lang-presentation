package part2_functions.lifting

// Pure function = gold

// Lifting AKA turning impure functions into pure functions with the help of
// data structures such as Option[A], Try[A], Either[E, A]

object Lifting {

  def main(args: Array[String]): Unit = {

    try {
      parseInt("Not a number!")
    } catch {
      case e: Exception => println("I just saved the main thread")
    }

    (1 to 10)
      .map(_.toString)
      .foreach(n => println(safeParseInt(n)))

  }

  def parseInt(number: String): Int = Integer parseInt number // unsafe

  def safeParseInt(number: String): Option[Int] = if number.forall(_.isDigit) then Some(Integer.parseInt(number)) else None


}

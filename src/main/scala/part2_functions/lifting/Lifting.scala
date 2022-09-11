package part2_functions.lifting

// Pure function = gold

// Lifting AKA turning impure functions into pure functions with the help of
// data structures such as Option[A], Try[A], Either[E, A]

object Lifting {

  def main(args: Array[String]): Unit =

    try parseInt("Not a number!")
    catch case _: Exception => println("I just saved the main thread from exploding :D")

    val vector = (1 to 10).toVector concat Vector("not a number :P", "not a number! :)", "not a number!")
    println(vector)

    val parsed: Seq[Int] = vector
      .map(_.toString)
      .flatMap(safeParseInt)

    parsed.foreach(println)
  

  def parseInt(number: String): Int = Integer parseInt number // unsafe
  
  // lift the heavy weights of preserving your programs from runtime exceptions :) 
  def safeParseInt(number: String): Option[Int] = if number.forall(_.isDigit) then Some(Integer.parseInt(number)) else None


}

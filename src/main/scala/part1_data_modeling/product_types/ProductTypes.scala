package part1_data_modeling.product_types

object ProductTypes {

  def main(args: Array[String]): Unit = {

    (Expression("list of additions", MathEquations(List("2+2=4", "1+1=2")))
        :: Expression("2D points", Graphics(List((1, 1), (2, 2), (3, 3))))
        :: Expression("program in bytes", Program(
        """
          |val s = "String"
          |val mappedString = s.map(_.toUpperCase)
          |println(mappedString)
          |""".stripMargin))
        :: Nil).foreach(display)

  }

  // commonalities are described in the product type
  final case class Expression(name: String, expr: Expr)

  // sum type
  sealed trait Expr

  // type alias
  type Point = (Int, Int) // tuple of two integers

  final case class MathEquations(equations: List[String]) extends Expr
  final case class Graphics(points: List[Point])          extends Expr
  final case class Program(program: String)               extends Expr

  type Display = Expression => Unit
  def display: Display = expression => expression. expr match
    case Graphics(points)         => points.foreach(p => println(s"(x=${p._1}, y=${p._2})"))
    case Program(program)         => println("Bytes in size: " + program.getBytes.sum)
    case MathEquations(equations) => equations.foreach(println)
}

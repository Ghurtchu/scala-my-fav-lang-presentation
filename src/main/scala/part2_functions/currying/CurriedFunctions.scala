package part2_functions.currying

object CurriedFunctions extends scala.App {

  // helps to reduce code duplication, just follow the types..

  object example1 {
    // takes an Int and returns a function which takes an Int and returns an Int
    def customizedAdder: Int => (Int => Int) = a => b => a + b
    
    // create more specialized functions
    val addToFive: Int => Int = customizedAdder(5)
    val addToTen: Int => Int = customizedAdder(10)

    // calculate values
    val result1: Int = addToFive(10) // 15
    val result2: Int = addToTen(5) // 15
  }

  object example2 {
    def transformPair[A, B, C](ab: (A, B))(f: (A, B) => C): C = f(ab._1, ab._2)

    val transformOneAndOne = transformPair((1, "1"))
    val transformTrueAndFalse = transformPair((true, false))

    val elevenStringified = transformOneAndOne { (a, b) =>
      a.toString concat b
    }
    val elevenSquared = transformOneAndOne { (a, b) =>
      Math.pow((a.toString concat b).toInt, 2)
    }

    val trueAndFalseCombined = transformTrueAndFalse(_ && _)
  }

}

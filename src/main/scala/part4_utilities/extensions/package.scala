package part4_utilities

package object extensions {

  extension[A](a: A)
    def display: Unit = println(a)
    def putInQuotes: String = s"'$a'"

  extension(str: String)

    def capitalizeFirst: String = {
      val splitted = str split " "
      splitted(0).toUpperCase.concat(splitted.drop(1).mkString(" ", " ", ""))
    }

    def surroundWith(char: Char) = s"$char$str$char"

}

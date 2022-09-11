package part1_data_modeling.smart_constructors

object SmartConstructors {
  
  def main(args: Array[String]): Unit = {

    val email = Email("johndoe@gmail.com")

    email match
      case Some(value) => sendMessage("Ey yo, sup?", value)
      case None        => println("incorrect email, try again")

    // shorter code using fold HOF
    email.fold(println("Incorrect")) { email =>
      sendMessage("Ey yo", email)
    }
    
    // even shorter code using Scala's underscore syntax
    email.fold(println("Incorrect"))(sendMessage("Ey yo", _))
    

  }

  case class Email private(value: String)

  object Email {
    def apply(value: String): Option[Email] = if isValid(value) then Some(new Email(value)) else None

    private def isValid(email: String): Boolean = email.contains("@") // simple solution for the sake of example
  }

  def sendMessage(message: String, phoneNumber: Email): Unit = ???

}

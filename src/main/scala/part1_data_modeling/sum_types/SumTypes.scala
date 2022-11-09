package part1_data_modeling.sum_types

import part1_data_modeling.sum_types.SumTypes.Contact._

object SumTypes {
  
  // sum type - finite amount of representations of a general interface
  // Scala 3 way of modeling Sum Types
  enum Contact:
    case Email(value: String)
    case Phone(number: Long)
    case Address(city: String, streetName: String)
    case LinkedinProfile(link: String)
    
  // In Scala 2 we would have used sealed traits and put the cases in the companion object of Contact
  object scala2 {
    
    sealed trait Contact
    
    object Contact {
      final case class Email(value: String)                      extends Contact
      final case class Phone(number: Long)                       extends Contact
      final case class Address(city: String, streetName: String) extends Contact
      final case class LinkedinProfile(link: String)             extends Contact
    }
    
  }

  def main(args: Array[String]): Unit = {

    import Contact._

    val email: Contact = Email("johndoe@gmail.com")
    val phone: Contact = Phone(555205407)
    val address: Contact = Address("Tbilisi", "Vazha-Pshavela Street")
    val linkedinProfile: Contact = LinkedinProfile("https://www.linkedin.com/in/ghurtchu/")
  }

}

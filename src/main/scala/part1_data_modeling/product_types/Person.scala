package part1_data_modeling.product_types

final case class Person(firstName: FirstName, lastName: LastName, age: Age, email: Email)

final case class FirstName(value: String) extends AnyVal
final case class LastName(value: String)  extends AnyVal
final case class Age(value: Int)          extends AnyVal
final case class Email(value: String)     extends AnyVal

object Person extends scala.App {

  def apply(firstName: String, lastName: String, age: Int, email: String): Person = new Person(
    FirstName(firstName),
    LastName(lastName),
    Age(age),
    Email(email)
  )

  val person = Person("Nika", "Ghurtchu", 15, "nghurtch@gmail.com")

}

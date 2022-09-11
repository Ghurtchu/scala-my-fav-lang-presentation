package part1_data_modeling.sum_types

import part1_data_modeling.sum_types.SumTypes.Contact._

object SumTypes {

  import scala.util.Try
  
  // sum type - finite amount of representations of a general interface
  enum Contact:
    case Email(value: String)
    case Phone(number: Long)
    case Address(city: String, streetName: String)
  
  def sendInvitation(contact: Contact): Unit = contact match
    case e @ Email(value)              => ???
    case p @ Phone(number)             => ???
    case a @ Address(city, streetName) => ???

  // sum type as a service
  sealed trait InvitationService[A <: Contact]:
    def sendInvitation(to: A, from: A, message: String): Unit
  

  final case class EmailService() extends InvitationService[Email]:
    override def sendInvitation(to: Email, from: Email, message: String): Unit = ???
  

  final case class PhoneService() extends InvitationService[Phone]:
    override def sendInvitation(to: Phone, from: Phone, message: String): Unit = ???
  

  final case class AddressService() extends InvitationService[Address]:
    override def sendInvitation(to: Address, from: Address, message: String): Unit = ???
  

}

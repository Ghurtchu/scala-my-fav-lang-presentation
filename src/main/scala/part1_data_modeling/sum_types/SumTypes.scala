package part1_data_modeling.sum_types

import part1_data_modeling.sum_types.SumTypes.Contact._

object SumTypes {

  import scala.util.Try
  
  // sum type - finite amount of representations of a general interface
  enum Contact:
    case Email(value: String)
    case Phone(number: Long)
    case Address(city: String, streetName: String)
  
  def sendInvitation(contact: Contact): Unit =
    InvitationService.fromContact(contact)
      .sendInvitation(???, ???, ???)

  // generalized sum type as a service
  sealed trait InvitationService[+A]:
    def sendInvitation[B >: A](to: B, from: B, message: String): Unit

  object InvitationService:
    def fromContact(contact: Contact): InvitationService[Contact] = contact match
      case Contact.Email(value)              => EmailService()
      case Contact.Phone(number)             => PhoneService()
      case Contact.Address(city, streetName) => AddressService()

  final case class EmailService() extends InvitationService[Contact.Email]:
    override def sendInvitation[B >: Contact.Email](to: B, from: B, message: String): Unit = println(s"Sending invitation via email to $to")
    
  final case class PhoneService() extends InvitationService[Phone]:
    override def sendInvitation[B >: Contact.Phone](to: B, from: B, message: String): Unit = println(s"Sending invitation via phone service to $to")
    
  final case class AddressService() extends InvitationService[Address]:
    override def sendInvitation[B >: Contact.Address](to: B, from: B, message: String): Unit = println(s"Sending invitation via mailman to $to")

}

package part1_data_modeling.smart_constructors

import scala.util.{Failure, Success, Try, Using}

// Either abstracts over errors and success channels and encapsulates them into a single data structure

object EitherExample {

  def main(args: Array[String]): Unit =
    val server   = Server()
    val endpoint = UserEndpoint()
    server.deploy(endpoint)
  
  class Server:
    
    def deploy(endpoint: UserEndpoint): Unit = 
      println("HTTP Server listening...")
      println("GET /user/{id}")
      while true do 
        print("id: ")
        val id: Try[Int] = Try(scala.io.StdIn.readLine().toInt)
        println {
          id.fold(
            _ => HttpResponse("Invalid id format", StatusCode.BadRequest),
            endpoint.getUserById
          )
        }
      

  final case class User(id: Int, name: String)
  final case class UserNotFound(msg: String)

  extension[A](a: A)
    def inQuotes: String = s"\"$a\""

  class UserEndpoint:
    def getUserById(id: Int): HttpResponse =
    UserRepoImpl.getById(id)
      .fold(
    notFound => HttpResponse(notFound.msg, StatusCode.NotFound),
    user     => HttpResponse(s"""{id: ${user.id}, name: ${user.name.inQuotes}}""", StatusCode.Ok))

  case class HttpResponse(body: String, statusCode: StatusCode)

  enum StatusCode:
    case Ok // 200
    case NotFound // 404
    case Created // 201
    case BadRequest // 400
    // and many more..

  trait UserRepo:
    def getById(id: Int): Either[UserNotFound, User]

  object UserRepoImpl extends UserRepo:

    val inMemoryDB: List[User] =
      (1 to 25).toList.map(id => User(id, scala.util.Random.nextString(10)))

    final override def getById(id: Int): Either[UserNotFound, User] =
      inMemoryDB
        .find(_.id == id)
        .toRight(UserNotFound(s"user with id = $id does not exist"))
}

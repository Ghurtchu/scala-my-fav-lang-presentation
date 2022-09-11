package part4_utilities.implicits

import part4_utilities.implicits.UsingAndGiven.Connection
import ConnectionProvider.connection

object UsingAndGiven {

  trait Connection

  trait DBClient {
    def executeQuery(query: String)(using connection: Connection): Unit = println(s"executing query => $query")
  }

  def main(args: Array[String]): Unit = {

    val dbClient = new DBClient {}

    // term inference
    dbClient.executeQuery("""DELETE from programming_language WHERE name != "Scala"""")

  }
}

object ConnectionProvider {
  // create connection once and provide for Dao instances
  given connection: Connection = new Connection{}
}

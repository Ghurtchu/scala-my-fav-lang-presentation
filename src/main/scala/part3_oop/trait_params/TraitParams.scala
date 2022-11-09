package part3_oop.trait_params

object TraitParams {

  type Query = String

  enum SearchCriteria {
    case Exact
    case NonExact
  }

  enum Operator {
    case And
    case Or

    def get: String = this match
      case Operator.And => "AND"
      case _            => "OR"
  }

  trait SearchQueryBuilder(queryParts: List[String]) {
    def buildQuery(criteria: SearchCriteria, operator: Operator): Query = {
      criteria match
        case SearchCriteria.Exact    => queryParts.map { q =>
          val splitted = q.split(" ")
          val updated  = splitted(0) concat ".keyword "

          updated concat splitted.drop(1).mkString(" ")
        }.mkString(s" ${operator.get} ")
        case SearchCriteria.NonExact => queryParts.mkString(s" ${operator.get} ")
    }
  }

  def main(args: Array[Query]): Unit = {

    val query = new SearchQueryBuilder(List("person.name == 'Peter'", "person.name == 'Parker'")) {}
      .buildQuery(SearchCriteria.Exact, Operator.And)

    println(query)

  }

}

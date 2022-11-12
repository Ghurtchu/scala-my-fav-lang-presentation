package part2_functions.patterns
import part2_functions.patterns.FunctorExample.Tree._

object FunctorExample {

  // higher-kinded type, abstracts over container with capability of 'map'
  // List, Option, Try, Future, Either ...
  trait Functor[F[_]] {
    def map[A, B](fa: F[A])(f: A => B): F[B]
  }

  implicit val optionFunctor: Functor[Option] = new Functor[Option] {
    override def map[A, B](fa: Option[A])(f: A => B): Option[B] = fa.map(f) // reuse Option#map
  }

  implicit val listFunctor: Functor[List] = new Functor[List] {
    override def map[A, B](fa: List[A])(f: A => B): List[B] = fa.map(f) // reuse List#map
  }

  // generalized version of map with the evidence of implicit functor instance in scope
  def map[F[_], A, B](container: F[A], f: A => B)(implicit functor: Functor[F]): F[B] = functor.map(container)(f)

  map[Option, Int, Int](Option(5), _ + 1) // Option(6)
  map[List, String, String](List("a", "b", "c"), _.toUpperCase) // List("A", "B", "C")

  case class User(name: String)

  map[Option, User, String](Some(User("John")), _.name) // Some("John")

  def main(args: Array[String]): Unit = {

    implicit val treeFunctor: Functor[Tree] = new Functor[Tree] {
      override def map[A, B](fa: Tree[A])(f: A => B): Tree[B] = fa.map(f)
    }

    println(map[Tree, Int, Int](Tree.node(Tree.leaf(2), Tree.leaf(3), 4), _ * 10))

  }

  enum Tree[+A]:

    case Leaf(value: A)
    case Node(left: Tree[A], right: Tree[A], value: A)

    import Tree.*

    def map[B](f: A => B): Tree[B] = this match {
      case Leaf(v) => Leaf(f(v))
      case Node(left, right, value) => Node(left map f, right map f, f(value))
    }

  object Tree:
    def leaf[A](value: A): Tree[A] = Tree.Leaf(value)
    def node[A](left: Tree[A], right: Tree[A], value: A): Tree[A] = Tree.Node(left, right, value)

}

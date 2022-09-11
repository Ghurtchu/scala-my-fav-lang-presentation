package part3_oop.mixin_composition

import scala.collection.mutable.ArrayBuffer

object StackableModifications {

  abstract class IntQueue {
    def get: Int
    def put(x: Int): Unit
  }

  class BasicIntQueue extends IntQueue {
    private val buffer = new ArrayBuffer[Int]
    override def get: Int = buffer remove 0
    override def put(x: Int): Unit = buffer += x
  }

  trait Doubling extends IntQueue {
    abstract override def put(x: Int): Unit = super.put(2 * x)
  }

  trait Incrementing extends IntQueue {
    abstract override def put(x: Int): Unit = super.put(x + 1)
  }

  trait Positive extends IntQueue {
    abstract override def put(x: Int): Unit =
      if x > 0 then super.put(x)
      else super.put(-x)
  }

  class DoublingQueue extends BasicIntQueue with Doubling

  // traits further to the right take effect first
  // 1) Incrementing
  // 2) Doubling
  class IncrementingDoublingQueue extends BasicIntQueue
    with Doubling
    with Incrementing

  class PositiveIncrementingDoublingQueue extends BasicIntQueue
    with Doubling
    with Incrementing
    with Positive

  def main(args: Array[String]): Unit = {
    println("start")

    val doublingQueue = DoublingQueue()
    doublingQueue.put(10)
    assert(doublingQueue.get == 20)

    val incrementingDoublingQueue = IncrementingDoublingQueue()
    incrementingDoublingQueue.put(1) // 3
    assert(incrementingDoublingQueue.get == 4)
    incrementingDoublingQueue.put(10) // 21
    assert(incrementingDoublingQueue.get == 22)

    val thirdQueue = PositiveIncrementingDoublingQueue()
    thirdQueue.put(-10) // Positive -> 10, Incrementing -> 11, Doubling -> 22
    assert(thirdQueue.get == 22)

    println("end")
  }

  // much more than this...

}

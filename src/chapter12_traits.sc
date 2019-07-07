import scala.collection.mutable.ArrayBuffer

trait Philosophical  {
  def philosophize() = {
    println("I think")
  }
}

class Animal

// Multiple withs can mixin multiple traits
class Frog extends Animal with Philosophical {
  override def toString: String = "I am frog"
}

class Point(val x : Int, val y: Int)

trait Rectangular {
  def topLeft : Point
  def bottomRight : Point

  def left = topLeft.x
  def right = bottomRight.x
  def width = right - left
}

// Values and no argument methods are equivalent.
class Rectangle(val topLeft : Point, val bottomRight : Point) extends Rectangular

// Take a thin interface that defines a single method and make it a rich
// interface with many methods.
class Rational(numer: Int, denom: Int) extends Ordered[Rational] {
  override def compare(that: Rational): Int = {
    (this.numer * that.denom) - (that.numer * this.denom)
  }
}

abstract class IntQueue {
  def pop() : Int
  def push(x : Int)
}

class BufferedIntQueue extends IntQueue {
  val buffer = new ArrayBuffer[Int]()
  override def pop() = buffer.remove(0)
  override def push(x : Int) = buffer += x
}

// Trait Doubler can only be mixed in to classes that extend IntQueue
trait Doubler extends IntQueue {
  abstract override def push(x : Int) = super.push(2 * x)
}

class DoubleIntQueue extends BufferedIntQueue with Doubler
// Instead can do
val q = new BufferedIntQueue with Doubler

trait Incrementer extends IntQueue {
  abstract override def push(x: Int): Unit = super.push(x + 1)
}

// Doubler is applied first since it is rightmost. The super refers
// to the next trait in line, which is Incrementer.
val q2 = new BufferedIntQueue with Incrementer with Doubler


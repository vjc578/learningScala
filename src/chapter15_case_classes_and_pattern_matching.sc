// Sealed class means that instances of the class can only be defined in this file
sealed abstract class Expr
case class Var(name: String) extends Expr
case class Number(num: Double) extends Expr
case class UnOp(operator: String, arg: Expr) extends Expr
case class BinOp(operator: String, left: Expr, right: Expr) extends Expr

// Case class does not require new because a factory method is defined.
val x = Var("hi")

// Case class takes params and makes them fields without val keyword
val name = Var("hi").name

// Case classes have toString, hashCode and equals methods that
// traverse the tree.

// Case classes also add a copy method
val op = BinOp("+", Number(5), Number(6))

// Makes a copy of op where only the
val opNegated = op.copy(operator = "-")

// Case matching allows you to pull out matching values
// and use them in the result.
def SimplifyTop(expr : Expr) : Expr = expr match {
  case UnOp("-", UnOp("-", e)) => e
  case BinOp("+", e, Number(0)) => e
  case BinOp("*", e, Number(1)) => e
  case _ => expr
}

def IsBinOp(expr : Expr) = expr match {
  // Uses wildcard matching instead of variable matching like
  // e in SimplifyTop because we don't care about any of the
  // matched values
  case BinOp(_, _, _) => println("yep, its a BinOp")
  case _ => println("Nope")
}

import math.{E, Pi}

// Pi is a constant, not a variable match, so this will always
// return "Nope".
def isPi = E match {
  case Pi => "Yep, pi"
  case _ => "Nope"
}

// isPi

// Can match against sequence classes. These are classes that
// extend trait Sequence
def listMatch(expr: List[String]): Unit = expr match {
  case List("0", "1", _*) => println("Found it")
  case _ => println("Nope")
}

// Can match against tuples
def tupleMatch(expr : Any): Unit = expr match {
  case (1, 2, 3) => println ("Found tuple")
  case _ => println("Nope")
}

// Type matching
def typeMatch(expr: Any) = expr match{
  case s: String => s.size
  case m: Map[_, _] => m.size
  case _ => -1
}

// Doesn't work, type erasure means the compiler removes runtime
// type information from the map, so at runtime it is not known what
// type of map it is.
def typeErasure(expr: Any) = expr match {
  case m : Map[Int, Int] => println("Int map")
  case _ => println("Not int map")
}

// e bins to the entire unary match value if a match is found
def absSimp(expr : Expr) : Expr = expr match {
  case UnOp("abs", e @ UnOp("abs", _)) => e
}

// pattern guard gets around the fact that a variable can only
// be defined an matched once. If is the guard.
def guard(expr: Expr) = expr match {
  case BinOp("+", x, y) if x == y => BinOp("*", x, Number(2))
}

// Cases evaluated in order, so later cases can be superset matches

// Option matching
def show(expr : Option[String]) = expr match {
  case Some(s) => s
  case None => "?"
}

// Matching vals/vars
val binOpNew = BinOp("+", Number(2), Number(3))
val BinOp(opAgain, left, right) = binOpNew

// Defines a function literal
val withDefault : Option[Int] => Int = {
  case Some(x) => x
  case None => 0
}

// Partial function, only defined for some values
val getSecond : PartialFunction[List[Int], Int] = {
  case x :: y :: _ => y
}

getSecond.isDefinedAt(List(1, 2, 3)) // true
getSecond.isDefinedAt(List()) // false
getSecond(List(1, 2))

// Pattern matching on loops. If there is no match it acts like a filter
val map = Map[Int, Int](1 -> 2, 3 -> 4)
for ((x, 2) <- map) println(x)
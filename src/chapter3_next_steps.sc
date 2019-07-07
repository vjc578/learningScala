import java.math.BigInteger
import scala.collection.mutable

val big = new java.math.BigInteger("1234")

val greetStrings = new Array[String](3)
greetStrings(0) = "Hello"
greetStrings(1) = "Hi"
greetStrings(2) = "Yes"
for (i <- 0 to 2) println(greetStrings(i))

val newArr = Array("Zero", "One", "Two")

val oneTwo = List("One", "Two")
val threeFour = List("Three", "Four")
val newList = oneTwo ::: threeFour
println(3 :: newList)

val pair = (3, 4)
println(pair._2)

var jetSet = mutable.Set("Boeing", "Cessna")
jetSet += "Cessna"
println(jetSet.contains("Leer"))
jetSet.foreach(println)

var myMap = Map(1 -> "hi", 3 -> "4")
myMap.foreach{case (key,value) => println(value)}
myMap.foreach(x => print(x._1))


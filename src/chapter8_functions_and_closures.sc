import scala.io.Source

def processFile(filename:String, width: Int) = {
  def processLine(line: String) = {
    if (line.length > width) {
      println(filename + ":" + line)
    }
  }

  val source = Source.fromFile(filename)
  for (line <- source.getLines()) {
    processLine(line)
  }
}

// Increase is a function literal.
var increase = (x: Int) => x + 1;
increase(10);

val someNumbers : List[Int] = List(1, 2, 3, 5)
someNumbers.foreach(println)

// Same way of writing filter, decreasingly concise.
// Placeholder sync
someNumbers.filter(_ > 1)
// Target type inference
someNumbers.filter(x => x > 1)
// Function literal, type not inferred.
someNumbers.filter((x: Int) => x > 1)

// Partial function application
val sum = (x : Int, y: Int, z: Int) => x + y + z
val sum2 = sum(1, _ : Int, _ : Int)
sum2 (5, 6)

// Closure
var more = 1
val plusMore = (x: Int) => x + more
plusMore(10)

// Var updated, plusMore will use updated value. Closures
// capture by reference.
more = 100
plusMore(100)

def echo (args : String*) = {
  for (arg <- args) {
    println(arg)
  }
}
echo("1", "2")


// Var args only works with functions not function
// literals?
// echo2("1", "2")
// Tail recursion, these two are equivalent.
def rec (x: Int) : Unit = {
  var plus1 = x - 1
  println(x)
  if (plus1 != 0)
   rec(plus1)
}

def rec2 (x : Int) : Unit = {
  var z = x
  while (z != 0) {
    println(z)
    z -= 1
  }
}

rec(10)
rec2(10)
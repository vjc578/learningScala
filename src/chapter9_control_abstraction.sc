import java.io.PrintWriter

object fileMatcher  {
  private var filesHere = (new java.io.File(".")).listFiles()
  def FilesMatching(query: String, matcher: String => Boolean) = {
    for (file <- filesHere) {
      if (matcher(file.getName)) println(file.getName)
    }
  }

  // Shorted version with placeholders. endsWidth is partially applied. This is
  // demonstrating closure support.
  def FilesEndingWith(query: String) = FilesMatching(query, _.endsWith(query))
}

val hasNeg = (x : List[Int]) => x.exists(_ < 0)
hasNeg(List(-1, 2, 3))

var x = List(-1, 2, 3)

def curriedSum(x: Int)(y: Int) = x + y
curriedSum(2)(3)

// Currying example to define a more natural control abstraction with {}
def withPrintWriter(file: java.io.File)(op : PrintWriter => Unit) = {
  val writer = new PrintWriter(file)
  try {
    op(writer)
  } finally {
    writer.close()
  }
}
withPrintWriter(new java.io.File("./x")) {
  writer => writer.println("hi")
}

val assertionsEnable = true
def byNameAssert(predicate: => Boolean) = {
  if (assertionsEnable && !predicate) throw new AssertionError()
}

byNameAssert(5 > 3)


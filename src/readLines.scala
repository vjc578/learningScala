import scala.io.Source

if (args.length > 0) {
  val lines = Source.fromFile(args(0)).getLines().toList
  def widthOfLength(line: String) = line.length.toString.length
  val maxWidth = widthOfLength(lines.reduceLeft((a,b) => if (a.length > b.length) a else b))
  for (line <- lines) {
    val numSpaces = maxWidth - widthOfLength(line)
    val padding = " " * numSpaces
    println(padding + line.length + " | " + line)
  }
}
else
  Console.err.println("Fail")

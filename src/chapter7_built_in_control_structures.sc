import java.io.{File, FileNotFoundException, FileReader}

val filesHere = (new java.io.File(".")).listFiles()
for (file <- filesHere) println(file)

for (i <- 1 to 4) println (i)

for (file <- filesHere if file.toString.endsWith("dylib")) println(file)

def grep(pattern: String) :Unit = {
  for (
    file <- filesHere
    if file.getName.endsWith(".scala");
    line <- scala.io.Source.fromFile(file).getLines().toList;
    trimmed = line.trim
    if trimmed.matches(pattern)
  ) println(file + ":" + trimmed)
}

grep("dylib")

try {
    val f = new FileReader("input.txt")
  } catch {
    case ex: FileNotFoundException => println("Fail")
  } finally {
    //f.close()
  }

val firstArg = "test"

val friend = firstArg match {
  case "test" => "pepper"
  case "chips" => "salsa"
  case _ => "huh?"
}

println(friend)

def makeRowSeq(row: Int) = {
  for (col <- 1 to 10) yield {
    val prod = (row * col).toString
    val padding = " " * (4 - prod.length)
    padding + prod
  }
}

def makeRow(row: Int) = makeRowSeq(row).mkString

def multiTable() = {
  val tableSeq = for(row <- 1 to 10) yield makeRow(row)
  tableSeq.mkString("\n")
}

println(multiTable())





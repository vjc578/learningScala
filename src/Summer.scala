import ChecksumAccumulator.calculate

// App trait defines main method which this code is put in?
object Summer extends App {
    for (arg <- args) println(arg + ": " + calculate(arg))
}

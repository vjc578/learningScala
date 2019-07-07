abstract class Element {
  // Method is abstract if it does not have a definition
  def contents : Array[String]
  def height = contents.length
  def width = if (height > 0) contents(0).length else 0

  def above(that: Element) = {
    new ArrayElement(contents ++ that.contents)
  }

  def beside(that: Element) : Element = {
    def fill (padding: Int, smaller: Element, larger: Element): Element = {
      new ArrayElement(
        (0 to smaller.height - 1).map(x => this.contents(x) + " " + that.contents(x)).toArray ++
          (smaller.height to larger.height - 1).map(x => " ".repeat(padding) + larger.contents(x)).toArray)
    }

    def padding (smaller: Element) =
      smaller.contents.foldLeft(0)((acc, line) => if (acc > line.length) acc else line.length)

    if (height > that.height)
      fill(padding(this), this, that)
    else
      fill(padding(that), that, this)
  }

  override def toString = contents.mkString("\n")
}

object Element {
  def elem (contents : Array[String]) = new ArrayElement(contents)

  def elem(contents: String) = new LineElement(contents)

  def elem(ch: Char, height: Int, width: Int) = new UniformElement(ch, height, width)
}

// Leave off parens in parameterless function if the function has no
// side effects, otherwise add them
// array.length vs println()

//class ArrayElement(conts : Array[String]) extends Element {
//  def contents = conts
  // Could do instead. Fields can override parameterless methods
  // and vice-versa
  // val contents : Array[String] = conts
//}

// Overrides contents, this a parametric field
class ArrayElement(override val contents : Array[String]) extends Element {
  final def demo() =  {
    println("I'm an ArrayElement")
  }
}

class Cat {
  val dangerous = false
}

class Tiger(override val dangerous : Boolean, private var age : Int) extends Cat

// Invoking superclass constructor
class LineElement(s: String) extends ArrayElement(Array(s)) {}

println((new LineElement("hi")).height)

class UniformElement(ch : Char,
                     override val width : Int,
                     override val height: Int)
extends ArrayElement(Array.fill(height)(ch.toString * width))


// Example of == that isn't reference inequality like in
// Java
val x = "abcd".substring(2)
val y = "abcd".substring(2)
x == y

// Nothing is a subtype of Int
def error(message: String) : Nothing = {
  throw new RuntimeException(message)
}

def divide(x: Int, y: Int) = {
  if (y == 0) error("Can't divide by zero")
  else x/y
}

// Value class will use type int when compiled to
// java bytecode
class Dollars (val amount : Int) extends AnyVal {
  override def toString: String = {
    "$" + amount
  }
}

// Tiny type
class Anchor(val text : String) extends AnyVal
class Style(val text: String) extends AnyVal
class Text(val text: String) extends AnyVal

def title(text: Text, anchor: Anchor, style: Style): String = {
  text.text + anchor.text + style.text
}

// This uses the compiler to ensure you don't get the order
// wrong.
title(new Text("hi"), new Anchor("bye"),
  new Style("hi"))


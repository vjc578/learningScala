class Rational (n : Int, d: Int) {
  require(d != 0)

  val numer: Int = n / gcd(n, d)
  val denom: Int = d / gcd(n, d)

  def this (n : Int) = this(n, 1)

  override def toString() = numer + "/" + denom

  def +(other : Rational) : Rational = {
    new Rational(numer * other.denom + other.numer * denom,
      denom * other.denom)
  }

  def *(other: Rational) : Rational = {
    new Rational(numer * other.numer, denom * other.denom)
  }

  def /(other: Rational) : Rational = {
    new Rational(numer * other.denom, denom * other.numer)
  }

  def +(other : Int) : Rational = {
    new Rational (numer + other * denom, denom)
  }

  def *(other : Int) : Rational = {
    new Rational (numer * other, denom)
  }

  def /(other: Int) : Rational = {
    new Rational (numer, denom * other)
  }

  private def gcd (a: Int, b:Int): Int = {
    if (b == 0) a else gcd(b, a % b)
  }
}

implicit def intToRational(x : Int) = new Rational (x)

val r = new Rational(5, 6)
var q = new Rational(5, 6)
var x = 2 + r
val x = List(1, 2, 3)
val h = x.head // first element
val t = x.tail // all but first
val z = 1 :: x // prepend 1 to list.

// Insertion Sort implemented with corecursive functions
def insertionSort(list : List[Int]) : List[Int] = {
  def insert(x : Int, list : List[Int]) : List[Int] = {
    if (list.isEmpty || x <= list.head) x :: list
    else list.head :: insertionSort(list.tail)
  }
  if (list.isEmpty) return Nil
  else insert(list.head, insertionSort(list.tail))
}
insertionSort(List(3, 5, 4))

// Alternative using pattern matching
def insertionSort2(list : List[Int]) : List[Int] = {
  def insert2(x : Int, list : List[Int]) : List[Int] = list match {
    case List() => x :: list
    case y :: ys => if (x <= y) x :: y :: ys else y :: insert2(x, ys)
  }
  list match {
    case List() => Nil
    case y :: ys => insert2(y, insertionSort2(ys))
  }
}

def append[T](x : List[T], y : List[T]) : List[T] = x match {
  case List() => y
  case z :: zs => z :: append(zs, y)
}

// O(n^2) and not tail recursive.
def reverse[T](x : List[T]) : List[T] = x match {
  case List() => Nil
  case z :: zs => reverse(zs) ::: List(z)
}

// O(n) and tail recursive. Note that the use of passing in a list
// as the accumulator makes this tail recursive 
// Note "soFar" is an accumulator, so this seems to imply we should
// use fold
def reverse2[T](list : List[T]) : List[T] = {
  def revHelper[T](x : T, list : List[T], soFar : List[T]) : List[T] = list match {
    case List() => x :: soFar
    case y :: zs => revHelper (y, zs, x :: soFar)
  }
  list match {
    case List() => Nil
    case x :: zs => revHelper(x, zs, Nil)
  }
}
val reved = reverse2(List(1, 2, 3, 4))

def reverse3[T](list : List[T]) : List[T] = list.foldLeft(
  List[T]())((y, x) => x :: y)

def less(x : scala.Int, y: scala.Int) = x < y

def mergeSort(list : List[Int]) : List[Int] = {
  def merge(left : List[Int], right: List[Int]) : List[Int] =
    (left, right) match {
      case (_, Nil) => left
      case (Nil, _) => right
      case (x :: xs, y :: ys) => 
        if (less(x,y)) x :: merge (xs, y :: ys)
        else y :: merge (x :: xs, ys)
    }

  val length = list.length
  if (length == 0 || length == 1) list
  else {
    val (leftList, rightList) = list.splitAt(length / 2)
    val left = mergeSort(leftList)
    val right = mergeSort(rightList)
    merge(left, right)
  }
}

// (1 /: list) says start with 1 and fold the elements in
// the list, taking each element and multiplying it by the
// current running product. These two forms are equivalent, the operand
// /: is equal to the foldLeft function. Last one just has more explicit
// params
def prod(list : List[Int]) = (1 /: list) (_ * _)
def prod2(list : List[Int]) = list.foldLeft(1)(_ * _)
def prod3(list: List[Int]) = list.foldLeft(1)((x, y) => x * y)

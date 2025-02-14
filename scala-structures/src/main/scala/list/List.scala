package list

trait List[+T] {
  def isEmpty: Boolean
  def head: T
  def tail: List[T]
  def prepend[U >: T](h: U): List[U]
  def append[U >: T](t: U): List[U]
  def reverse: List[T]
}

trait ListCompanion[L[_] <: List[?]] {
  def empty[T]: L[T]
}

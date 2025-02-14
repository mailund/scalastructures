package list

trait List[T] {
  def isEmpty: Boolean
  def head: T
  def tail: List[T]
  def prepend(t: T): List[T]
  def append(t: T): List[T]
}

trait ListCompanion[L[_] <: List[?]] {
  def empty[T]: L[T]
}

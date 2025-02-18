package list

private class Lazy[+T](expr: => T):
  lazy val value: T = expr

enum LazyList[+T] extends List[T]:
  case Empty
  case Cons(h: T, t: Lazy[LazyList[T]])

  def isEmpty = this match
    case Empty => true
    case _     => false

  def head: T = this match
    case Cons(h, _) => h
    case Empty      => throw new NoSuchElementException("head of empty list")

  def tail: LazyList[T] = this match
    case Cons(_, t) => t.value
    case Empty => throw new UnsupportedOperationException("tail of empty list")

  def prepend[U >: T](elem: U): LazyList[U] =
    Cons(elem, Lazy(this))

  def append[U >: T](elem: U): LazyList[U] = this match
    case Empty         => Cons(elem, Lazy(Empty))
    case Cons(h, tail) => Cons(h, Lazy(tail.value.append(elem)))

  def rev[U >: T](acc: LazyList[U]): LazyList[U] = this match
    case Empty         => acc
    case Cons(h, tail) => tail.value.rev(Cons(h, Lazy(acc)))

  def reverse: LazyList[T] = rev(Empty)

object LazyList extends ListCompanion[LazyList] {
  override def empty[T]: LazyList[T] = LazyList.Empty
}

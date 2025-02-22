package list

enum LazyList[+T] extends List[T]:
  case Empty
  case Cons(h: T, t: () => LazyList[T])

  def isEmpty = this match
    case Empty => true
    case _     => false

  def head: T = this match
    case Cons(h, _) => h
    case Empty      => throw new NoSuchElementException("head of empty list")

  def tail: LazyList[T] = this match
    case Cons(_, t) => t()
    case Empty => throw new UnsupportedOperationException("tail of empty list")

  def prepend[U >: T](elem: U): LazyList[U] = Cons(elem, () => this)

  def append[U >: T](elem: U): LazyList[U] = this match
    case Empty         => Cons(elem, () => Empty)
    case Cons(h, tail) => Cons(h, () => tail().append(elem))

  def rev[U >: T](acc: LazyList[U]): LazyList[U] = this match
    case Empty      => acc
    case Cons(h, t) => t().rev(Cons(h, () => acc))

  def reverse: LazyList[T] = rev(Empty)

object LazyList extends ListCompanion[LazyList] {
  override def empty[T]: LazyList[T] = Empty
}

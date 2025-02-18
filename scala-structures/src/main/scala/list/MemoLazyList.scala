package list

class LazyVal[+T](_value: => T) {
  lazy val value: T = _value
}
enum MemoLazyList[+T] extends List[T]:
  case Empty
  case Cons(h: T, t: LazyVal[MemoLazyList[T]])

  def isEmpty = this match
    case Empty => true
    case _     => false

  def head: T = this match
    case Cons(h, _) => h
    case Empty      => throw new NoSuchElementException("head of empty list")

  def tail: MemoLazyList[T] = this match
    case Cons(_, t) => t.value
    case Empty => throw new UnsupportedOperationException("tail of empty list")

  def prepend[U >: T](elem: U): MemoLazyList[U] = Cons(elem, LazyVal(this))

  def append[U >: T](elem: U): MemoLazyList[U] = this match
    case Empty         => Cons(elem, LazyVal(Empty))
    case Cons(h, tail) => Cons(h, LazyVal(tail.value.append(elem)))

  def rev[U >: T](acc: MemoLazyList[U]): MemoLazyList[U] = this match
    case Empty      => acc
    case Cons(h, t) => t.value.rev(Cons(h, LazyVal(acc)))

  def reverse: MemoLazyList[T] = rev(Empty)

object MemoLazyList extends ListCompanion[MemoLazyList] {
  override def empty[T]: MemoLazyList[T] = Empty
}

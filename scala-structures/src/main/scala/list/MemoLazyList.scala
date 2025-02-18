package list

class LazyVal[+T](_value: => T) {
  lazy val value: T = _value
}
case object MEmpty extends MemoLazyList[Nothing]
case class MCons[+T](h: T, t: LazyVal[MemoLazyList[T]]) extends MemoLazyList[T]

sealed abstract class MemoLazyList[+T] extends List[T]:
  def isEmpty = this match
    case MEmpty => true
    case _      => false

  def head: T = this match
    case MCons(h, _) => h
    case MEmpty      => throw new NoSuchElementException("head of empty list")

  def tail: MemoLazyList[T] = this match
    case MCons(_, t) => t.value
    case MEmpty => throw new UnsupportedOperationException("tail of empty list")

  def prepend[U >: T](elem: U): MemoLazyList[U] = MCons(elem, LazyVal(this))

  def append[U >: T](elem: U): MemoLazyList[U] = this match
    case MEmpty         => MCons(elem, LazyVal(MEmpty))
    case MCons(h, tail) => MCons(h, LazyVal(tail.value.append(elem)))

  def rev[U >: T](acc: MemoLazyList[U]): MemoLazyList[U] = this match
    case MEmpty      => acc
    case MCons(h, t) => t.value.rev(MCons(h, LazyVal(acc)))

  def reverse: MemoLazyList[T] = rev(MEmpty)

object MemoLazyList extends ListCompanion[MemoLazyList] {
  override def empty[T]: MemoLazyList[T] = MEmpty
}

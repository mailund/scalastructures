package list

enum EnumList[+T] extends List[T]:
  case Empty
  case Cons(h: T, t: EnumList[T])

  def isEmpty: Boolean = this match
    case Empty => true
    case _     => false

  def head: T = this match
    case Cons(h, _) => h
    case Empty      => throw new NoSuchElementException("head of empty list")

  def tail: EnumList[T] = this match
    case Cons(_, t) => t
    case Empty => throw new UnsupportedOperationException("tail of empty list")

  def prepend[U >: T](elem: U): EnumList[U] =
    Cons(elem, this)

  def append[U >: T](elem: U): EnumList[U] = this match
    case Empty         => Cons(elem, Empty)
    case Cons(h, tail) => Cons(h, tail.append(elem))

  private def rev[U >: T](acc: EnumList[U]): EnumList[U] = this match
    case Empty         => acc
    case Cons(h, tail) => tail.rev(Cons(h, acc))

  val reverse = rev(Empty)

object EnumList extends ListCompanion[EnumList] {
  override def empty[T]: EnumList[T] = Empty
}

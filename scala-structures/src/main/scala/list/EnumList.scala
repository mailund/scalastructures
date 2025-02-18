package list

/*
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

=> java.lang.ExceptionInInitializerError

 */

/*
sealed abstract class EnumList[+T] extends List[T]:
  def isEmpty: Boolean
  def head: T
  def tail: EnumList[T]
  val reverse: EnumList[T] = rev(Empty)(this)

  def prepend[U >: T](elem: U): EnumList[U] = Cons(elem, this)
  def append[U >: T](elem: U): EnumList[U] = this match
    case Empty         => Cons(elem, Empty)
    case Cons(h, tail) => Cons(h, tail.append(elem))


case object Empty extends EnumList[Nothing]:
  def isEmpty: Boolean = true
  def head: Nothing = throw new NoSuchElementException("head of empty list")
  def tail: EnumList[Nothing] = throw new UnsupportedOperationException(
    "tail of empty list"
  )

case class Cons[+T](h: T, t: EnumList[T]) extends EnumList[T]:
  def isEmpty: Boolean = false
  def head: T = h
  def tail: EnumList[T] = t

def rev[T](acc: EnumList[T])(list: EnumList[T]): EnumList[T] = list match
  case Empty         => acc
  case Cons(h, tail) => rev(Cons(h, acc))(tail)

object EnumList extends ListCompanion[EnumList]:
  def empty[T]: EnumList[T] = Empty

  => java.lang.ExceptionInInitializerError

 */

/*
sealed abstract class EnumList[+T] extends List[T]:
  def isEmpty: Boolean
  def head: T
  def tail: EnumList[T]

  def prepend[U >: T](elem: U): EnumList[U] = Cons(elem, this)
  def append[U >: T](elem: U): EnumList[U] = this match
    case Empty         => Cons(elem, Empty)
    case Cons(h, tail) => Cons(h, tail.append(elem))

  def reverse: List[T] =
    var acc: EnumList[T] = Empty
    var lst = this
    while !lst.isEmpty do
      acc = acc.prepend(lst.head)
      lst = lst.tail
    acc

case object Empty extends EnumList[Nothing]:
  def isEmpty: Boolean = true
  def head: Nothing = throw new NoSuchElementException("head of empty list")
  def tail: EnumList[Nothing] = throw new UnsupportedOperationException(
    "tail of empty list"
  )

case class Cons[+T](h: T, t: EnumList[T]) extends EnumList[T]:
  def isEmpty: Boolean = false
  def head: T = h
  def tail: EnumList[T] = t

object EnumList extends ListCompanion[EnumList]:
  def empty[T]: EnumList[T] = Empty

  // Works, but not pure FP

 */

sealed abstract class EnumList[+T] extends List[T]:
  def isEmpty: Boolean
  def head: T
  def tail: EnumList[T]

  def prepend[U >: T](elem: U): EnumList[U] = Cons(elem, this)
  def append[U >: T](elem: U): EnumList[U] = this match
    case Empty         => Cons(elem, Empty)
    case Cons(h, tail) => Cons(h, tail.append(elem))

  def reverse: List[T] =
    var acc: EnumList[T] = Empty
    var lst = this
    while !lst.isEmpty do
      acc = acc.prepend(lst.head)
      lst = lst.tail
    acc

case object Empty extends EnumList[Nothing]:
  def isEmpty: Boolean = true
  def head: Nothing = throw new NoSuchElementException("head of empty list")
  def tail: EnumList[Nothing] = throw new UnsupportedOperationException(
    "tail of empty list"
  )

case class Cons[+T](h: T, t: EnumList[T]) extends EnumList[T]:
  def isEmpty: Boolean = false
  def head: T = h
  def tail: EnumList[T] = t

object EnumList extends ListCompanion[EnumList]:
  def empty[T]: EnumList[T] = Empty

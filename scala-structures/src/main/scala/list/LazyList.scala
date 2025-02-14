// package list

// sealed trait LazyList[+T] extends List[T]:
//   def isEmpty: Boolean
//   def head: T
//   def tail: LazyList[T]
//   def prepend[U >: T](elem: U): LazyList[U]
//   def append[U >: T](elem: U): LazyList[U]

//   protected def rev[U >: T](acc: LazyList[U]): LazyList[U]

//   def reverse: LazyList[T] = rev(Empty)

// case object Empty extends LazyList[Nothing]:
//   def isEmpty: Boolean = true
//   def head: Nothing = throw new NoSuchElementException("head of empty list")
//   def tail: LazyList[Nothing] = throw new UnsupportedOperationException(
//     "tail of empty list"
//   )
//   def prepend[U](elem: U): LazyList[U] = Cons(elem, Empty)
//   def append[U](elem: U): LazyList[U] = Cons(elem, Empty)

//   def rev[U](acc: LazyList[U]): LazyList[U] = acc

// class Cons[+T](h: T, _tailThunk: => LazyList[T]) extends LazyList[T]:
//   lazy val tail: LazyList[T] = _tailThunk // Lazily computed, cached

//   def isEmpty: Boolean = false
//   def head: T = h

//   def prepend[U >: T](elem: U): LazyList[U] =
//     Cons(elem, this)

//   def append[U >: T](elem: U): LazyList[U] =
//     Cons(h, tail.append(elem))

//   def rev[U >: T](acc: LazyList[U]): LazyList[U] =
//     tail.rev(Cons(h, acc))

// object Cons:
//   def apply[T](h: T, _tailThunk: => LazyList[T]): Cons[T] =
//     new Cons(h, _tailThunk)

//   def unapply[T](ll: LazyList[T]): Option[(T, LazyList[T])] = ll match
//     case c: Cons[T] => Some((c.head, c.tail))
//     case _          => None

// object LazyList extends ListCompanion[LazyList] {
//   override def empty[T]: LazyList[T] = Empty
// }

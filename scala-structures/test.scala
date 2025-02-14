sealed trait MyLazyList[+T]:
  def isEmpty: Boolean
  def head: T
  def tail: MyLazyList[T]
  def prepend[U >: T](elem: U): MyLazyList[U]
  def append[U >: T](elem: U): MyLazyList[U]

case object Empty extends MyLazyList[Nothing]:
  def isEmpty: Boolean = true
  def head: Nothing = throw new NoSuchElementException("head of empty list")
  def tail: MyLazyList[Nothing] = throw new UnsupportedOperationException(
    "tail of empty list"
  )
  def prepend[U](elem: U): MyLazyList[U] = Cons(elem, Empty)
  def append[U](elem: U): MyLazyList[U] = Cons(elem, Empty)

class Cons[+T](h: T, _tailThunk: => MyLazyList[T]) extends MyLazyList[T]:
  lazy val tail: MyLazyList[T] = _tailThunk // Lazily computed, cached

  def isEmpty: Boolean = false
  def head: T = h

  def prepend[U >: T](elem: U): MyLazyList[U] =
    Cons(elem, this)

  def append[U >: T](elem: U): MyLazyList[U] =
    Cons(h, tail.append(elem))

  // Manually define equals and hashCode to match case class behavior
  override def equals(obj: Any): Boolean = obj match
    case cons: Cons[T @unchecked] => head == cons.head && tail == cons.tail
    case _                        => false

  override def hashCode(): Int = 31 * head.hashCode() + tail.hashCode()

object Cons:
  def apply[T](h: T, _tailThunk: => MyLazyList[T]): Cons[T] =
    new Cons(h, _tailThunk)

@main def test(): Unit =
  var counter = 0
  val lazyList = Cons(
    1, {
      counter += 1
      Empty
    }
  )

  println(counter) // 0 (tail not accessed yet)
  println(lazyList.tail) // Evaluates tail once, counter = 1
  println(counter) // 1
  println(lazyList.tail) // Uses cached value, counter still = 1
  println(counter) // 1

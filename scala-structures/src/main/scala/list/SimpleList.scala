package list
import _root_.scala.collection.immutable.List as ScalaList

class SimpleList[+T](private val elements: ScalaList[T]) extends List[T] {

  override def isEmpty: Boolean = elements.isEmpty

  override def head: T = elements.head
  override def tail: List[T] = new SimpleList[T](elements.tail)
  override def prepend[U >: T](h: U): List[U] = new SimpleList[U](h :: elements)
  override def append[U >: T](t: U): List[U] = new SimpleList[U](elements :+ t)

  override def reverse: List[T] = new SimpleList[T](elements.reverse)
}

object SimpleList extends ListCompanion[SimpleList] {
  override def empty[T]: SimpleList[T] = new SimpleList[T](Nil)
}

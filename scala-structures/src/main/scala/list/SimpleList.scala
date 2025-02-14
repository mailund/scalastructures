package list
import _root_.scala.collection.immutable.List as ScalaList

class SimpleList[T](private val elements: ScalaList[T]) extends List[T] {

  override def isEmpty: Boolean = elements.isEmpty

  override def head: T = elements.head
  override def tail: List[T] = new SimpleList[T](elements.tail)
  override def prepend(t: T): List[T] = new SimpleList[T](t :: elements)
  override def append(t: T): List[T] = new SimpleList[T](elements :+ t)
}

object SimpleList extends ListCompanion[SimpleList] {
  override def empty[T]: SimpleList[T] = new SimpleList[T](Nil)
}

package queue

class SimpleQueue[+T](private val elements: List[T]) extends Queue[T] {

  override def isEmpty: Boolean = elements.isEmpty

  // For linked lists, this is O(n) because we have to traverse the entire list
  // to add an element to the end. This is what makes the simple queue simple,
  // and a little unattractive for large queues.
  override def enqueue[U >: T](u: U): Queue[U] =
    new SimpleQueue[U](elements :+ u)
  override def top(): T = elements.head
  override def dequeue(): Queue[T] = new SimpleQueue[T](elements.tail)
  override def size: Int = elements.size
}

object SimpleQueue extends QueueCompanion[SimpleQueue] {
  override def empty[T]: SimpleQueue[T] = new SimpleQueue[T](Nil)
}

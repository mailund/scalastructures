package queue

// FIXME: This isn't implemented yet. I just set it up so I can do it in the morning before work
class TwoListsQueue[T](private val front: List[T], back: List[T])
    extends Queue[T] {

  override def isEmpty: Boolean = front.isEmpty && back.isEmpty

  // For linked lists, this is O(n) because we have to traverse the entire list
  // to add an element to the end. This is what makes the simple queue simple,
  // and a little unattractive for large queues.
  override def enqueue(t: T): Queue[T] = new TwoListsQueue[T](front, t :: back)
  override def top(): Option[T] = front.headOption
  override def dequeue(): Queue[T] = new TwoListsQueue[T](front.tail, back)
  override def size: Int = front.size + back.size
}

object TwoListsQueue extends QueueCompanion[TwoListsQueue] {
  override def empty[T]: TwoListsQueue[T] =
    new TwoListsQueue[T](List.empty, List.empty)
}

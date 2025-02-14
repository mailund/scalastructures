package queue

class TwoListsQueue[T](private val front: List[T], back: List[T])
    extends Queue[T] {

  private def rebalance: TwoListsQueue[T] = front match
    case Nil => new TwoListsQueue(back.reverse, Nil)
    case _   => this

  override def isEmpty: Boolean = front.isEmpty && back.isEmpty

  override def enqueue(t: T): Queue[T] =
    new TwoListsQueue[T](front, t :: back).rebalance
  override def top(): T = front.head
  override def dequeue(): Queue[T] =
    new TwoListsQueue[T](front.tail, back).rebalance
  override def size: Int = front.size + back.size
}

object TwoListsQueue extends QueueCompanion[TwoListsQueue] {
  override def empty[T]: TwoListsQueue[T] =
    new TwoListsQueue[T](Nil, Nil)
}

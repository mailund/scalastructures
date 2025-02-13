trait Queue[T] {
  def isEmpty: Boolean
  def enqueue(t: T): Queue[T]
  def top(): Option[T]
  def dequeue(): Queue[T]
  def size: Int
}

trait QueueCompanion[Q[_] <: Queue[?]] {
  def empty[T]: Q[T]
}

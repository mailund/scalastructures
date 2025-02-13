import queue.*

class SimpleQueueSpec extends QueueSpec {
  override def createQueue[T]: Queue[T] = SimpleQueue.empty[T]
}

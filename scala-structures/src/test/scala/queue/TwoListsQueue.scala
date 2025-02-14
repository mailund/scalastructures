import queue.*

class TwoListsQueueSpec extends QueueSpec {
  override def createQueue[T]: Queue[T] = TwoListsQueue.empty[T]
}

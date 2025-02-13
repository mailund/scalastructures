import org.scalatest.flatspec.AnyFlatSpec
import org.scalatest.matchers.should.Matchers

trait QueueSpec extends AnyFlatSpec with Matchers {
  def createQueue[T]: Queue[T]

  "A Queue" should "be empty when created" in {
    val queue = createQueue[Int]
    queue.isEmpty should be(true)
  }

  it should "allow enqueueing elements" in {
    val queue = createQueue[Int]
    val newQueue = queue.enqueue(1)
    newQueue.isEmpty should be(false)
  }

  it should "allow dequeueing elements" in {
    val queue = createQueue[Int]
    val newQueue = queue.enqueue(1).enqueue(2)
    newQueue.top() should be(Some(1))
    val dequeuedQueue = newQueue.dequeue()
    dequeuedQueue.top() should be(Some(2))
  }

  it should "return the correct size" in {
    val queue = createQueue[Int]
    val newQueue = queue.enqueue(1).enqueue(2)
    newQueue.size should be(2)
    val dequeuedQueue = newQueue.dequeue()
    dequeuedQueue.size should be(1)
  }
}

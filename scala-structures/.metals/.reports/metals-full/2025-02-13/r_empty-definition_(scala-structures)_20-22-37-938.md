error id: `<none>`.
file://<WORKSPACE>/src/main/scala/SimpleQueue.scala
empty definition using pc, found symbol in pc: `<none>`.
empty definition using semanticdb
|empty definition using fallback
non-local guesses:
	 -

Document text:

```scala
class SimpleQueue[T](elements: List[T]) extends Queue[T] {

  override def empty(): Queue[T] = new SimpleQueue[T](List.empty)
  override def isEmpty: Boolean = elements.isEmpty
  override def enqueue(t: T): Queue[T] = {
    new SimpleQueue[T](this.elements :+ t)
  }

  override def top(): Option[T] = elements.headOption

  override def dequeue(): Queue[T] = {
    val elements = if this.elements.isEmpty then List.empty else this.elements.tail
    new SimpleQueue[T](elements)
  }
  override def size: Int = elements.size
}
```

#### Short summary: 

empty definition using pc, found symbol in pc: `<none>`.
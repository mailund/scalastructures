file://<WORKSPACE>/src/main/scala/SimpleQueue.scala
### java.lang.AssertionError: assertion failed: position not set for fromNullable(<empty>) # -1 of class dotty.tools.dotc.ast.Trees$Apply in <WORKSPACE>/src/main/scala/SimpleQueue.scala

occurred in the presentation compiler.

presentation compiler configuration:


action parameters:
offset: 257
uri: file://<WORKSPACE>/src/main/scala/SimpleQueue.scala
text:
```scala
class SimpleQueue[T] extends Queue[T] {
  private val elements: List[T] = List.empty

  override def isEmpty: Boolean = elements.isEmpty

  override def empty(): Queue[T] = new SimpleQueue[T]

  override def enqueue(t: T): Queue[T] = {
    val newQueue = _.@@
    newQueue.elements = elements :+ t
    newQueue
  }

  override def top(): Option[T] = elements.headOption

  override def dequeue(): Queue[T] = {
    val newQueue = new SimpleQueue[T]
    newQueue.elements = elements.tail
    newQueue
  }

  override def size: Int = elements.size
}
```



#### Error stacktrace:

```
scala.runtime.Scala3RunTime$.assertFailed(Scala3RunTime.scala:8)
	dotty.tools.dotc.typer.Typer$.assertPositioned(Typer.scala:76)
	dotty.tools.dotc.typer.Typer.typed(Typer.scala:3657)
	dotty.tools.dotc.typer.Applications.extMethodApply(Applications.scala:2642)
	dotty.tools.dotc.typer.Applications.extMethodApply$(Applications.scala:434)
	dotty.tools.dotc.typer.Typer.extMethodApply(Typer.scala:148)
	dotty.tools.dotc.typer.Applications.tryApplyingExtensionMethod(Applications.scala:2687)
	dotty.tools.dotc.typer.Applications.tryApplyingExtensionMethod$(Applications.scala:434)
	dotty.tools.dotc.typer.Typer.tryApplyingExtensionMethod(Typer.scala:148)
	dotty.tools.dotc.interactive.Completion$Completer.tryApplyingReceiverToExtension$1(Completion.scala:561)
	dotty.tools.dotc.interactive.Completion$Completer.$anonfun$23(Completion.scala:604)
	scala.collection.immutable.List.flatMap(List.scala:294)
	scala.collection.immutable.List.flatMap(List.scala:79)
	dotty.tools.dotc.interactive.Completion$Completer.extensionCompletions(Completion.scala:601)
	dotty.tools.dotc.interactive.Completion$Completer.selectionCompletions(Completion.scala:449)
	dotty.tools.dotc.interactive.Completion$.computeCompletions(Completion.scala:221)
	dotty.tools.dotc.interactive.Completion$.rawCompletions(Completion.scala:80)
	dotty.tools.pc.completions.Completions.enrichedCompilerCompletions(Completions.scala:114)
	dotty.tools.pc.completions.Completions.completions(Completions.scala:136)
	dotty.tools.pc.completions.CompletionProvider.completions(CompletionProvider.scala:139)
	dotty.tools.pc.ScalaPresentationCompiler.complete$$anonfun$1(ScalaPresentationCompiler.scala:150)
```
#### Short summary: 

java.lang.AssertionError: assertion failed: position not set for fromNullable(<empty>) # -1 of class dotty.tools.dotc.ast.Trees$Apply in <WORKSPACE>/src/main/scala/SimpleQueue.scala
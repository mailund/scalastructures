file://<WORKSPACE>/src/main/scala/list/LazyList.scala
### java.lang.AssertionError: assertion failed

occurred in the presentation compiler.

presentation compiler configuration:


action parameters:
offset: 106
uri: file://<WORKSPACE>/src/main/scala/list/LazyList.scala
text:
```scala
package list

sealed trait LazyList[+T] extends List[T]:
  def isEmpty: Boolean
  def head: T
  def tail: @@MyLazyList[T]
  def prepend[U >: T](elem: U): MyLazyList[U]
  def append[U >: T](elem: U): MyLazyList[U]

case object Empty extends MyLazyList[Nothing]:
  def isEmpty: Boolean = true
  def head: Nothing = throw new NoSuchElementException("head of empty list")
  def tail: MyLazyList[Nothing] = throw new UnsupportedOperationException(
    "tail of empty list"
  )
  def prepend[U](elem: U): MyLazyList[U] = Cons(elem, Empty)
  def append[U](elem: U): MyLazyList[U] = Cons(elem, Empty)

class Cons[+T](h: T, _tailThunk: => MyLazyList[T]) extends MyLazyList[T]:
  lazy val tail: MyLazyList[T] = _tailThunk // Lazily computed, cached

  def isEmpty: Boolean = false
  def head: T = h

  def prepend[U >: T](elem: U): MyLazyList[U] =
    Cons(elem, this)

  def append[U >: T](elem: U): MyLazyList[U] =
    Cons(h, tail.append(elem))

object Cons:
  def apply[T](h: T, _tailThunk: => MyLazyList[T]): Cons[T] =
    new Cons(h, _tailThunk)

object LazyList extends ListCompanion[LazyList] {
  override def empty[T]: LazyList[T] = Empty
}

```



#### Error stacktrace:

```
scala.runtime.Scala3RunTime$.assertFailed(Scala3RunTime.scala:11)
	dotty.tools.dotc.core.Annotations$LazyAnnotation.tree(Annotations.scala:138)
	dotty.tools.dotc.core.Annotations$Annotation$Child$.unapply(Annotations.scala:244)
	dotty.tools.dotc.typer.Namer.insertInto$1(Namer.scala:489)
	dotty.tools.dotc.typer.Namer.addChild(Namer.scala:500)
	dotty.tools.dotc.typer.Namer$Completer.register$1(Namer.scala:967)
	dotty.tools.dotc.typer.Namer$Completer.registerIfChildInCreationContext$$anonfun$1(Namer.scala:976)
	scala.runtime.function.JProcedure1.apply(JProcedure1.java:15)
	scala.runtime.function.JProcedure1.apply(JProcedure1.java:10)
	scala.collection.immutable.List.foreach(List.scala:334)
	dotty.tools.dotc.typer.Namer$Completer.registerIfChildInCreationContext(Namer.scala:976)
	dotty.tools.dotc.typer.Namer$Completer.complete(Namer.scala:860)
	dotty.tools.dotc.core.SymDenotations$SymDenotation.completeFrom(SymDenotations.scala:175)
	dotty.tools.dotc.core.Denotations$Denotation.completeInfo$1(Denotations.scala:190)
	dotty.tools.dotc.core.Denotations$Denotation.info(Denotations.scala:192)
	dotty.tools.dotc.core.Types$NamedType.info(Types.scala:2436)
	dotty.tools.dotc.core.Types$TermLambda.dotty$tools$dotc$core$Types$TermLambda$$_$compute$1(Types.scala:3994)
	dotty.tools.dotc.core.Types$TermLambda.foldArgs$2(Types.scala:4001)
	dotty.tools.dotc.core.Types$TermLambda.dotty$tools$dotc$core$Types$TermLambda$$_$compute$1(Types.scala:4606)
	dotty.tools.dotc.core.Types$TermLambda.dotty$tools$dotc$core$Types$TermLambda$$depStatus(Types.scala:4021)
	dotty.tools.dotc.core.Types$TermLambda.dependencyStatus(Types.scala:4035)
	dotty.tools.dotc.core.Types$TermLambda.isResultDependent(Types.scala:4057)
	dotty.tools.dotc.core.Types$TermLambda.isResultDependent$(Types.scala:3951)
	dotty.tools.dotc.core.Types$MethodType.isResultDependent(Types.scala:4096)
	dotty.tools.dotc.typer.TypeAssigner.assignType(TypeAssigner.scala:301)
	dotty.tools.dotc.typer.TypeAssigner.assignType$(TypeAssigner.scala:16)
	dotty.tools.dotc.typer.Typer.assignType(Typer.scala:148)
	dotty.tools.dotc.ast.tpd$.Apply(tpd.scala:49)
	dotty.tools.dotc.ast.tpd$TreeOps$.appliedToTermArgs$extension(tpd.scala:976)
	dotty.tools.dotc.ast.tpd$.New(tpd.scala:554)
	dotty.tools.dotc.ast.tpd$.New(tpd.scala:545)
	dotty.tools.dotc.core.Annotations$Annotation$Child$.makeChildLater$1(Annotations.scala:233)
	dotty.tools.dotc.core.Annotations$Annotation$Child$.later$$anonfun$1(Annotations.scala:236)
	dotty.tools.dotc.core.Annotations$LazyAnnotation.tree(Annotations.scala:142)
	dotty.tools.dotc.core.Annotations$Annotation$Child$.unapply(Annotations.scala:244)
	dotty.tools.dotc.typer.Namer.insertInto$1(Namer.scala:489)
	dotty.tools.dotc.typer.Namer.addChild(Namer.scala:500)
	dotty.tools.dotc.typer.Namer$Completer.register$1(Namer.scala:967)
	dotty.tools.dotc.typer.Namer$Completer.registerIfChildInCreationContext$$anonfun$1(Namer.scala:976)
	scala.runtime.function.JProcedure1.apply(JProcedure1.java:15)
	scala.runtime.function.JProcedure1.apply(JProcedure1.java:10)
	scala.collection.immutable.List.foreach(List.scala:334)
	dotty.tools.dotc.typer.Namer$Completer.registerIfChildInCreationContext(Namer.scala:976)
	dotty.tools.dotc.typer.Namer$Completer.complete(Namer.scala:860)
	dotty.tools.dotc.core.SymDenotations$SymDenotation.completeFrom(SymDenotations.scala:175)
	dotty.tools.dotc.core.Denotations$Denotation.completeInfo$1(Denotations.scala:190)
	dotty.tools.dotc.core.Denotations$Denotation.info(Denotations.scala:192)
	dotty.tools.dotc.core.SymDenotations$ClassDenotation.computeMembersNamed(SymDenotations.scala:2161)
	dotty.tools.dotc.core.SymDenotations$ClassDenotation.membersNamed(SymDenotations.scala:2131)
	dotty.tools.dotc.core.SymDenotations$ClassDenotation.findMember(SymDenotations.scala:2182)
	dotty.tools.dotc.core.Types$Type.go$1(Types.scala:783)
	dotty.tools.dotc.core.Types$Type.findMember(Types.scala:968)
	dotty.tools.dotc.typer.TypeAssigner.selectionType(TypeAssigner.scala:164)
	dotty.tools.dotc.typer.TypeAssigner.selectionType$(TypeAssigner.scala:16)
	dotty.tools.dotc.typer.Typer.selectionType(Typer.scala:148)
	dotty.tools.dotc.typer.Typer.typedSelectWithAdapt(Typer.scala:734)
	dotty.tools.dotc.typer.Typer.typeSelectOnTerm$1(Typer.scala:996)
	dotty.tools.dotc.typer.Typer.typedSelect(Typer.scala:1034)
	dotty.tools.dotc.typer.Typer.typedNamed$1(Typer.scala:3475)
	dotty.tools.dotc.typer.Typer.typedUnadapted(Typer.scala:3584)
	dotty.tools.dotc.typer.Typer.typed(Typer.scala:3662)
	dotty.tools.dotc.typer.Typer.typed(Typer.scala:3666)
	dotty.tools.dotc.typer.Typer.typedExpr(Typer.scala:3777)
	dotty.tools.dotc.typer.Applications.realApply$1(Applications.scala:1051)
	dotty.tools.dotc.typer.Applications.typedApply(Applications.scala:1244)
	dotty.tools.dotc.typer.Applications.typedApply$(Applications.scala:434)
	dotty.tools.dotc.typer.Typer.typedApply(Typer.scala:148)
	dotty.tools.dotc.typer.Typer.typedUnnamed$1(Typer.scala:3500)
	dotty.tools.dotc.typer.Typer.typedUnadapted(Typer.scala:3585)
	dotty.tools.dotc.typer.Typer.typed(Typer.scala:3662)
	dotty.tools.dotc.typer.Typer.typed(Typer.scala:3666)
	dotty.tools.dotc.typer.Typer.typedExpr(Typer.scala:3777)
	dotty.tools.dotc.typer.Typer.typedValDef(Typer.scala:2835)
	dotty.tools.dotc.typer.Typer.typedNamed$1(Typer.scala:3479)
	dotty.tools.dotc.typer.Typer.typedUnadapted(Typer.scala:3584)
	dotty.tools.dotc.typer.Typer.typed(Typer.scala:3662)
	dotty.tools.dotc.typer.Typer.typed(Typer.scala:3666)
	dotty.tools.dotc.typer.Typer.traverse$1(Typer.scala:3688)
	dotty.tools.dotc.typer.Typer.typedStats(Typer.scala:3734)
	dotty.tools.dotc.typer.Typer.typedPackageDef(Typer.scala:3297)
	dotty.tools.dotc.typer.Typer.typedUnnamed$1(Typer.scala:3534)
	dotty.tools.dotc.typer.Typer.typedUnadapted(Typer.scala:3585)
	dotty.tools.dotc.typer.Typer.typed(Typer.scala:3662)
	dotty.tools.dotc.typer.Typer.typed(Typer.scala:3666)
	dotty.tools.dotc.typer.Typer.typedExpr(Typer.scala:3777)
	dotty.tools.dotc.typer.TyperPhase.typeCheck$$anonfun$1(TyperPhase.scala:47)
	scala.runtime.function.JProcedure1.apply(JProcedure1.java:15)
	scala.runtime.function.JProcedure1.apply(JProcedure1.java:10)
	dotty.tools.dotc.core.Phases$Phase.monitor(Phases.scala:507)
	dotty.tools.dotc.typer.TyperPhase.typeCheck(TyperPhase.scala:53)
	dotty.tools.dotc.typer.TyperPhase.$anonfun$4(TyperPhase.scala:99)
	scala.collection.Iterator$$anon$6.hasNext(Iterator.scala:479)
	scala.collection.Iterator$$anon$9.hasNext(Iterator.scala:583)
	scala.collection.immutable.List.prependedAll(List.scala:152)
	scala.collection.immutable.List$.from(List.scala:685)
	scala.collection.immutable.List$.from(List.scala:682)
	scala.collection.IterableOps$WithFilter.map(Iterable.scala:900)
	dotty.tools.dotc.typer.TyperPhase.runOn(TyperPhase.scala:98)
	dotty.tools.dotc.Run.runPhases$1$$anonfun$1(Run.scala:343)
	scala.runtime.function.JProcedure1.apply(JProcedure1.java:15)
	scala.runtime.function.JProcedure1.apply(JProcedure1.java:10)
	scala.collection.ArrayOps$.foreach$extension(ArrayOps.scala:1323)
	dotty.tools.dotc.Run.runPhases$1(Run.scala:336)
	dotty.tools.dotc.Run.compileUnits$$anonfun$1(Run.scala:383)
	dotty.tools.dotc.Run.compileUnits$$anonfun$adapted$1(Run.scala:395)
	dotty.tools.dotc.util.Stats$.maybeMonitored(Stats.scala:69)
	dotty.tools.dotc.Run.compileUnits(Run.scala:395)
	dotty.tools.dotc.Run.compileSources(Run.scala:282)
	dotty.tools.dotc.interactive.InteractiveDriver.run(InteractiveDriver.scala:161)
	dotty.tools.pc.CachingDriver.run(CachingDriver.scala:45)
	dotty.tools.pc.WithCompilationUnit.<init>(WithCompilationUnit.scala:31)
	dotty.tools.pc.WithSymbolSearchCollector.<init>(PcCollector.scala:340)
	dotty.tools.pc.PcDocumentHighlightProvider.<init>(PcDocumentHighlightProvider.scala:17)
	dotty.tools.pc.ScalaPresentationCompiler.documentHighlight$$anonfun$1(ScalaPresentationCompiler.scala:182)
```
#### Short summary: 

java.lang.AssertionError: assertion failed
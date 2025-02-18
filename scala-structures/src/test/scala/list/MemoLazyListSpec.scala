package list

import list.*

class MemoLazyListSpec extends ListSpec {
  override def createList[T]: List[T] = MemoLazyList.empty[T]
}

package list

import list.*

class SimpleListSpec extends ListSpec {
  override def createList[T]: List[T] = SimpleList.empty[T]
}

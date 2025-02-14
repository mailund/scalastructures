package list

import list.*

class EnumListSpec extends ListSpec {
  override def createList[T]: List[T] = EnumList.empty[T]
}

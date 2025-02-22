package list

import org.scalatest.flatspec.AnyFlatSpec
import org.scalatest.matchers.should.Matchers

trait ListSpec extends AnyFlatSpec with Matchers {
  def createList[T]: list.List[T]

  it should "create an empty list" in {
    val list = createList[Int]
    list.isEmpty shouldBe true
  }

  it should "add an element using prepend" in {
    val list = createList[Int].prepend(10)
    list.isEmpty shouldBe false
    list.head shouldBe 10
    list.tail.isEmpty shouldBe true
  }

  it should "add an element using append" in {
    val list = createList[Int].append(20)
    list.isEmpty shouldBe false
    list.head shouldBe 20
    list.tail.isEmpty shouldBe true
  }

  it should "maintain order with multiple prepends" in {
    // Prepending two elements: last prepended comes as head.
    val list = createList[Int].prepend(10).prepend(20)
    list.head shouldBe 20
    list.tail.head shouldBe 10
  }

  it should "maintain order with multiple appends" in {
    // Appending two elements: first appended becomes head.
    val list = createList[Int].append(10).append(20)
    list.head shouldBe 10
    list.tail.head shouldBe 20
  }

  it should "return an empty list when reverse is called on an empty list" in {
    val list = createList[Int]
    list.reverse.isEmpty shouldBe true
  }

  it should "reverse the order of a non-empty list created by appending elements" in {
    val list = createList[Int].append(1).append(2).append(3)
    val reversed = list.reverse
    reversed.head shouldBe 3
    reversed.tail.head shouldBe 2
    reversed.tail.tail.head shouldBe 1
    reversed.tail.tail.tail.isEmpty shouldBe true
  }
}

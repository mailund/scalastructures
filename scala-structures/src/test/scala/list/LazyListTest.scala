package list

import org.scalatest.flatspec.AnyFlatSpec
import org.scalatest.matchers.should.Matchers

class LazyListTest extends AnyFlatSpec with Matchers {

  // Infinite list of integers starting from n.
  def count(n: Int): LazyList[Int] = Cons(n, () => count(n + 1))

  def take[T](list: LazyList[T], n: Int): LazyList[T] =
    def take_[T](acc: LazyList[T])(list: LazyList[T], n: Int): LazyList[T] =
      (n, list) match
        case (0, _) | (_, Empty) => acc
        case (_, Cons(h, t))     => take_(Cons(h, acc))(t, n - 1)
    take_(Empty)(list, n).reverse

  it should "take the first n elements" in {
    val list = count(1)
    list.head shouldBe 1
    list.tail.head shouldBe 2
    list.tail.tail.head shouldBe 3
    list.tail.tail.tail.isEmpty shouldBe true
  }
}

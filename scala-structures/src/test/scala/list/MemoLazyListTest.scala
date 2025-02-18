package list

import org.scalatest.flatspec.AnyFlatSpec
import org.scalatest.matchers.should.Matchers

class MemoLazyListTest extends AnyFlatSpec with Matchers {

  import MemoLazyList.*

  // Infinite list of integers starting from n.
  def count(n: Int): MemoLazyList[Int] = Cons(n, LazyVal(count(n + 1)))

  def take[T](list: MemoLazyList[T], n: Int): MemoLazyList[T] =
    def take_[T](
        acc: MemoLazyList[T]
    )(list: MemoLazyList[T], n: Int): MemoLazyList[T] =
      (n, list) match
        case (0, _) | (_, Empty) => acc
        case (_, Cons(h, t))     => take_(Cons(h, LazyVal(acc)))(t.value, n - 1)
    take_(Empty)(list, n).reverse

  it should "take the first n elements" in {
    val list = count(1)
    list.head shouldBe 1
    list.tail.head shouldBe 2
    list.tail.tail.head shouldBe 3
    list.tail.tail.tail.isEmpty shouldBe false // the list is infinite
  }

  it should "also work with take" in {
    val list = count(1)
    val first3 = take(list, 3)
    first3.head shouldBe 1
    first3.tail.head shouldBe 2
    first3.tail.tail.head shouldBe 3
    first3.tail.tail.tail.isEmpty shouldBe true
  }
}

package chapter4

class Consumer[T](t: T) {
  def use[U >: T](u: U) = {
    println(u)
  }
}

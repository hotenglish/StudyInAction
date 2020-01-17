package guid

class SubConstructor(n: Int, m: Int) {

  require(m != 0, m)

  def this(a: Int) = this(a, 10)

  def compare(a: Int, b: Int) = if (b == 0) a % b else a

  var X = compare(n, m)

  def A = n * X

  def B = m * X

  def <(that: SubConstructor) = this.A * that.B < that.A * this.B

  def selfDiction(that: SubConstructor): SubConstructor = if (this.<(that)) that else this

}

object SubConstructor {

  def main(args: Array[String]): Unit = {
    var subAdd = new SubConstructor(4, 6)
    var add = subAdd.compare(7, 5)
    println(add)
    println(subAdd.X)
  }

}
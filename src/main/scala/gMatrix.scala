/**
  * Created by greatstone on 2015. 12. 17..
  */
object gMatrix {
  def answer(input: Iterator[String]) = {
    var output = ""
    val T = input.next.toInt
    (1 to T) foreach { caseNum =>
      val inputValue = new InputParam(input)
      val result = solve(inputValue)
      output += s"Case #$caseNum: $result\n"
    }
    output.lines
  }

  def solve(input: InputParam) = {
    val matrix =
      for (i <- 0 until input.N) yield
        for (j <- 0 until input.N) yield
          (input.A(i) * (i + 1) + input.B(j) * (j + 1) + input.C) % input.X

    val maxs = for (i <- 0 to input.N - input.K; j <- 0 to input.N - input.K) yield {
      var maxList: List[Long] = List()
      for (k <- 0 until input.K) {
        maxList ++= matrix(i + k).slice(j, j + input.K)
      }
      maxList.max
    }
    maxs.sum
  }

  //noinspection ForwardReference
  class InputParam(input: Iterator[String]) {
    val N = line(0).toInt
    val K = line(1).toInt
    val C = line(2).toLong
    val X = line(3).toLong
    val A = input.next.split(" ").map(_.toLong)
    val B = input.next.split(" ").map(_.toLong)
    private val line = input.next.split(" ")
  }

}

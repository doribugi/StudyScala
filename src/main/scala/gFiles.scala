/**
  * Created by greatstone on 2015. 12. 7..
  */
object gFiles {
  def solve(input: Iterator[String]) = {
    var result = ""

    val T = input.next.toInt
    (1 to T) foreach { caseNum =>
      val N = input.next.toInt
      val percentInfo = for (pi <- 1 to N) yield {
        val line = input.next.split(" ")
        (line.head.toLong, line.last.toLong)
      }
      val fileNum = computeFileNum(percentInfo)
      result += s"Case #$caseNum: $fileNum\n"
    }
    result.lines
  }

  def computeFileNum(percentInfo: Seq[(Long, Long)]) = {
    percentInfo.last match {
      case (100, k) => k
      case (_, _) =>
        val (lower, upper) = percentInfo.dropWhile(_._1 == 0).map { case (p, k) => (100 * k / (p + 1), 100 * k / p) }.unzip
        if (upper.min - lower.max == 1) upper.min else -1l
    }
  }
}

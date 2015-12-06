// google page address https://code.google.com/codejam/contest/4284487/dashboard

import java.net.URL

import scala.io.Source

/**
  * Created by greatstone on 2015. 11. 28..
  */
object gRanks extends App {

  val filename = "A-large-practice"
  val writer = new java.io.PrintWriter(filename + ".out")
  val result = solve(getClass.getResource(s"gRanks/$filename.in"))

  def solve(resource: URL) = {
    val lines = Source.fromURL(resource).getLines
    val T = lines.next.toInt

    var result = ""
    for (caseNum <- 1 to T) {
      val p = lines.next.toInt
      val s = lines.next.split(" ").map(_.toInt)
      val n = lines.next.toInt
      val competitions = for (game <- 1 to n) yield {
        val competition = lines.next.split(" ")
        (competition.head.toInt, competition.tail)
      }
      val m = lines.next.toInt

      result += s"Case #$caseNum:\n"
      result += determineRank(s, competitions, m).map(x => s"${x.rank}: ${x.name}\n").mkString
    }
    result.lines
  }

  def determineRank(s: Array[Int], competitions: Seq[(Int, Array[String])], m: Int) = {

    val scores =
      for ((w, winners) <- competitions;
           (winner, index) <- winners.zipWithIndex)
        yield (winner, w * s(index))
    val scoreMap = scores.groupBy(_._1).mapValues(_.unzip._2)

    val totalScores = for ((athlete, score) <- scoreMap.toArray) yield
      (athlete, score.sorted(Ordering[Int].reverse).take(m).sum)

    val rankTable = totalScores.sortWith((x, y) => (x._2 > y._2) || (x._2 == y._2 && x._1 < y._1))

    var rank = 1
    var previousScore = -1
    for ((score, index) <- rankTable.zipWithIndex) yield {
      if (previousScore != score._2) {
        rank = index + 1
      }
      previousScore = score._2
      Result(rank, score._1)
    }
  }

  case class Result(rank: Int, name: String)

  writer.println(result)
  writer.close()
}

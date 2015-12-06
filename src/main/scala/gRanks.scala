// google page address https://code.google.com/codejam/contest/4284487/dashboard

import java.net.URL

import scala.collection.mutable
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
      val competitions = for (game <- 1 to n) yield lines.next.split(" ")
      val m = lines.next.toInt

      result += s"Case #$caseNum:\n"
      result += determineRank(s, competitions, m)
    }
    result.lines
  }

  def determineRank(s: Array[Int], competitions: Seq[Array[String]], m: Int) = {
    var scores = Map[String, mutable.MutableList[Int]]()
    competitions.foreach { x =>
      val w = x.head.toInt
      for ((winner, index) <- x.tail.zipWithIndex) {
        if (scores.contains(winner)) {
          val score = w * s(index)
          scores(winner) += score
        }
        else {
          val score = w * s(index)
          scores += (winner -> mutable.MutableList(score))
        }
      }
    }

    val totalScores = for ((athlete, score) <- scores.toArray) yield
      (athlete, score.sorted(Ordering[Int].reverse).take(m).sum)

    val rankTable = totalScores.sortWith((x, y) => (x._2 > y._2) || (x._2 == y._2 && x._1 < y._1))
    var rank = 1
    var previousScore = -1
    var res = ""
    for ((score, index) <- rankTable.zipWithIndex) {
      if (previousScore != score._2) {
        rank = index + 1
      }
      previousScore = score._2
      res += s"$rank: ${score._1}\n"
    }
    res
  }

  writer.println(result)
  writer.close()
}

// google page address https://code.google.com/codejam/contest/4284487/dashboard

import java.net.URL

import scala.collection.mutable
import scala.io.Source

/**
  * Created by greatstone on 2015. 11. 28..
  */
object gRanks {
  def solve(resource: URL): Unit = {
    val lines = Source.fromURL(resource).getLines
    val T = lines.next.toInt

    for (caseNum <- 1 to T) {
      var scores = Map[String, mutable.MutableList[Int]]()

      val P = lines.next.toInt
      val Si = lines.next.split(" ").map(_.toInt)
      val N = lines.next.toInt
      for (game <- 1 to N) {
        val result = lines.next.split(" ")
        val Wi = result(0).toInt
        var resIdx = 1
        while (resIdx < result.size) {
          if (scores.contains(result(resIdx))) {
            val score = Wi * Si(resIdx - 1)
            scores(result(resIdx)) += score
          }
          else {
            val score = Wi * Si(resIdx - 1)
            scores += (result(resIdx) -> mutable.MutableList(score))
          }
          resIdx += 1
        }
      }
      val M = lines.next.toInt

      var totalScores = mutable.MutableList[(Int, String)]()
      for (score <- scores) {
        var totalScore = 0
        val sortedScore = score._2.sorted(Ordering[Int].reverse)
        for (n <- 0 until M if n < score._2.length) {
          totalScore += sortedScore(n)
        }
        totalScores += (totalScore -> score._1)
      }

      val rankTable = totalScores.sortWith((x, y) => (x._1 > y._1) || (x._1 == y._1 && x._2 < y._2))
      println(s"Case #$caseNum:")
      var rank = 1
      var nextRank = 1
      var previousScore = -1
      for (row <- rankTable) {
        if (previousScore != row._1) {
          rank = nextRank
        }
        println(s"$rank: ${row._2}")
        previousScore = row._1
        nextRank += 1
      }
    }
  }
}

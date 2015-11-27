import org.scalatest.FunSuite

/**
  * Created by greatstone on 2015. 11. 28..
  */
class gRanksSuite extends FunSuite {
  test("small set") {
    gRanks.solve(getClass.getResource("gRanks/A-small-practice.in.txt"))
  }
  test("large set") {
    gRanks.solve(getClass.getResource("gRanks/A-large-practice.in.txt"))
  }
}

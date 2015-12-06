import org.scalatest.FunSuite

/**
  * Created by greatstone on 2015. 11. 28..
  */
class gRanksSuite extends FunSuite {
  test("small set") {
    val actual = gRanks.solve(getClass.getResource("gRanks/A-small-practice.in"))

    val expected = io.Source.fromURL(getClass.getResource("gRanks/A-small-practice.out")).getLines
    assert(expected, actual)
  }

  test("large set") {
    val actual = gRanks.solve(getClass.getResource("gRanks/A-large-practice.in"))
    val expected = io.Source.fromURL(getClass.getResource("gRanks/A-large-practice.out")).getLines
    assert(expected, actual)
  }

  def assert(expected: Iterator[String], actual: Iterator[String]) {
    while (actual.hasNext) {
      assert(actual.next.trim === expected.next.trim)
    }
    assert(expected.hasNext === false, "Finished too fast.")
  }
}

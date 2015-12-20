import org.scalatest.FunSuite

import scala.io.Source

/**
  * Created by greatstone on 2015. 12. 17..
  */
class gMatrixSuite extends FunSuite {
  test("sample set") {
    val input =
      """3
        |1 1 1 5
        |1
        |1
        |2 1 5 11
        |1 2
        |3 4
        |3 2 3 109
        |6 4 3
        |2 1 5""".stripMargin.lines

    val actual = gMatrix.answer(input)

    val expected =
      """Case #1: 3
        |Case #2: 19
        |Case #3: 80""".stripMargin.lines

    assert(expected, actual)
  }

  test("small set") {
    val input = Source.fromURL(getClass.getResource("gMatrix/D-small-practice.in.txt")).getLines
    val actual = gMatrix.answer(input)
    val expected = io.Source.fromURL(getClass.getResource("gMatrix/D-small-practice.out.txt")).getLines
    assert(expected, actual)

    //    val writer = new java.io.PrintWriter("D-small-practice.out.txt")
    //    writer.println(actual.mkString("\n"))
    //    writer.close()
  }

  test("large set") {
    val input = Source.fromURL(getClass.getResource("gMatrix/D-large-practice.in.txt")).getLines
    val actual = gMatrix.answer(input)
    //    val expected = io.Source.fromURL(getClass.getResource("gMatrix/D-large-practice.out.txt")).getLines
    //    assert(expected, actual)

    val writer = new java.io.PrintWriter("D-large-practice.out.txt")
    writer.println(actual.mkString("\n"))
    writer.close()
  }

  def assert(expected: Iterator[String], actual: Iterator[String]) {
    while (actual.hasNext) {
      assert(actual.next.trim === expected.next.trim)
    }
    assert(expected.hasNext === false, "Finished too fast.")
  }
}

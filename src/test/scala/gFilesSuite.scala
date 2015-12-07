import org.scalatest.FunSuite

import scala.io.Source

/**
  * Created by greatstone on 2015. 11. 28..
  */
class gFilesSuite extends FunSuite {
  test("sample set") {
    val input =
      """3
        |2
        |20 1
        |100 5
        |10
        |25 241
        |27 262
        |43 407
        |44 413
        |57 536
        |64 601
        |67 637
        |84 789
        |95 893
        |96 903
        |10
        |0 0
        |8 2
        |8 2
        |17 4
        |30 7
        |39 9
        |69 16
        |73 17
        |82 19
        |91 21""".stripMargin.lines

    val actual = gFiles.solve(input)

    val expected =
      """Case #1: 5
        |Case #2: -1
        |Case #3: 23""".stripMargin.lines

    assert(expected, actual)
  }

  test("small set") {
    val input = Source.fromURL(getClass.getResource("gFiles/B-small-practice.in.txt")).getLines
    val actual = gFiles.solve(input)
    val expected = io.Source.fromURL(getClass.getResource("gFiles/B-small-practice.out.txt")).getLines
    assert(expected, actual)

    //    val writer = new java.io.PrintWriter("B-small-practice.out.txt")
    //    writer.println(actual.mkString("\n"))
    //    writer.close()
  }

  test("large set") {
    val input = Source.fromURL(getClass.getResource("gFiles/B-large-practice.in.txt")).getLines
    val actual = gFiles.solve(input)
    val expected = io.Source.fromURL(getClass.getResource("gFiles/B-large-practice.out.txt")).getLines
    assert(expected, actual)

    //    val writer = new java.io.PrintWriter("B-large-practice.out.txt")
    //    writer.println(actual.mkString("\n"))
    //    writer.close()
  }

  def assert(expected: Iterator[String], actual: Iterator[String]) {
    while (actual.hasNext) {
      assert(actual.next.trim === expected.next.trim)
    }
    assert(expected.hasNext === false, "Finished too fast.")
  }
}

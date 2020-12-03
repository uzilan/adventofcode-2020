package adventofcode

import adventofcode.Utils.toStringList
import io.kotest.core.spec.style.StringSpec
import io.kotest.matchers.shouldBe

class Day3Test : StringSpec({
    val input = """
      ..##.......
      #...#...#..
      .#....#..#.
      ..#.#...#.#
      .#...##..#.
      ..#.##.....
      .#.#.#....#
      .#........#
      #.##...#...
      #...##....#
      .#..#...#.#
  """.trimIndent()

    "part 1"() {
        Day3.countTrees(input.toStringList()) shouldBe 7
    }

    "part 2"() {
        Day3.countTreesInEachSlope(input.toStringList()) shouldBe 336
    }
})
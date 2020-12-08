package adventofcode

import io.kotest.core.spec.style.StringSpec
import io.kotest.matchers.shouldBe
import java.io.File

class Day8Test :StringSpec({

    val input = File("src/test/resources/day8.txt").readLines()

    "part 1"() {
        Day8.part1(input) shouldBe 5
    }

    "part 2"() {
        Day8.part2(input) shouldBe 8
    }
})
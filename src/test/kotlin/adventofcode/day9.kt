package adventofcode

import io.kotest.core.spec.style.StringSpec
import io.kotest.matchers.shouldBe
import java.io.File

class Day9Test : StringSpec({

    val input = File("src/test/resources/day9.txt").readLines()

    "part 1"() {
        Day9.part1(input, 5, 5) shouldBe 127
    }

    "part 2"() {
        Day9.part2(input, 5, 5) shouldBe 62
    }
})
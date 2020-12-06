package adventofcode

import io.kotest.core.spec.style.StringSpec
import io.kotest.matchers.shouldBe
import java.io.File

class Day6Test : StringSpec({

    val input = File("src/test/resources/day6.txt").readText()

    "part 1"() {
        Day6.part1(input) shouldBe 11
    }

    "part 2"() {
        Day6.part2(input) shouldBe 6
    }
})
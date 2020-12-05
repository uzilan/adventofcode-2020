package adventofcode

import io.kotest.core.spec.style.StringSpec
import io.kotest.matchers.shouldBe
import java.io.File

class Day5Test : StringSpec({

    val input = File("src/test/resources/day5.txt").readLines()

    "part 1"() {
        Day5.part1(input) shouldBe 820
    }
})

package adventofcode

import io.kotest.core.spec.style.StringSpec
import io.kotest.matchers.shouldBe
import java.io.File

class Day13Test : StringSpec({

    val input = File("src/test/resources/day13.txt").readLines()

    "part 1"() {
        Day13.part1(input) shouldBe 295
    }
})
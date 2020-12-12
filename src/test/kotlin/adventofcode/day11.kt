package adventofcode

import adventofcode.Day11.Companion.adjacentSeats
import io.kotest.core.spec.style.StringSpec
import io.kotest.matchers.shouldBe
import java.io.File

class Day11Test : StringSpec({

    val input = File("src/test/resources/day11.txt").readLines()

    "part 1"() {
        Day11(adjacentSeats).stabiliseAndCount(input) shouldBe 37
    }
})
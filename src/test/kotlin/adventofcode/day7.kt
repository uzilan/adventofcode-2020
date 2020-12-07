package adventofcode

import io.kotest.core.spec.style.StringSpec
import io.kotest.matchers.shouldBe
import java.io.File

class Day7Test : StringSpec({

    val input = File("src/test/resources/day7.txt").readLines()
    val input2 = File("src/test/resources/day7_2.txt").readLines()

    "part 1"() {
        Day7.part1(input) shouldBe 4
    }

    "part 2_1"() {
        Day7.part2(input) shouldBe 32
    }

    "part 2_2"() {
        Day7.part2(input2) shouldBe 126
    }
})
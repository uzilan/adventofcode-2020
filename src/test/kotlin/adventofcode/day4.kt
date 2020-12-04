package adventofcode

import io.kotest.core.spec.style.StringSpec
import io.kotest.matchers.shouldBe
import java.io.File

class Day4Test : StringSpec({

    "part 1"() {
        val input = File("src/test/resources/day4.txt").readText()
        Day4.part1(input) shouldBe 2
    }

    "part 2 - valid"() {
        val input = File("src/test/resources/day4valid.txt").readText()
        Day4.part2(input) shouldBe 4
    }

    "part 2 - invalid"() {
        val input = File("src/test/resources/day4invalid.txt").readText()
        Day4.part2(input) shouldBe 0
    }
})

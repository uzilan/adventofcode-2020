package adventofcode

import io.kotest.core.spec.style.StringSpec
import io.kotest.matchers.shouldBe
import java.io.File

class Day16Test : StringSpec({

    val input = File("src/test/resources/day16.txt").readText()

    "part 1"() {
        Day16.part1(input) shouldBe 71
    }

    "part 2"() {
        Day16.part2(input) shouldBe 71
    }

})
package adventofcode

import io.kotest.core.spec.style.StringSpec
import io.kotest.matchers.shouldBe
import java.io.File

class Day22Test : StringSpec({

    val input = File("src/test/resources/day22.txt").readText()

    "part 1"() {
        Day22.part1(input) shouldBe 306
    }
})
package adventofcode

import io.kotest.core.spec.style.StringSpec
import io.kotest.matchers.shouldBe
import java.io.File

class Day20Test : StringSpec({

    val input = File("src/test/resources/day20.txt").readText()

    "part 1" {
        Day20.part1(input) shouldBe 2
    }
})

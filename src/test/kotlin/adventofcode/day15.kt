package adventofcode

import io.kotest.core.spec.style.FreeSpec
import io.kotest.matchers.shouldBe

class Day15Test : FreeSpec({
    "part 1" - {
        listOf(
            listOf(0, 3, 6) to 436,
            listOf(1, 3, 2) to 1,
            listOf(2, 1, 3) to 10,
            listOf(1, 2, 3) to 27,
            listOf(2, 3, 1) to 78,
            listOf(3, 2, 1) to 438,
            listOf(3, 1, 2) to 1836,
        ).forEach { (input, expected) ->
            "input $input should give $expected" - {
                Day15.part1(input) shouldBe expected
            }
        }
    }
})
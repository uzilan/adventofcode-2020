package adventofcode

import io.kotest.core.spec.style.StringSpec
import io.kotest.matchers.shouldBe

class Day23Test : StringSpec({
    "part 1"() {
        Day23.part1(389125467) shouldBe 67384529
    }
})
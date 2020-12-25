package adventofcode

import io.kotest.core.spec.style.StringSpec
import io.kotest.matchers.shouldBe

class Day25Test : StringSpec({
    "part 1"() {
        Day25.part1(5764801, 17807724) shouldBe 14897079
    }

})
package adventofcode

import adventofcode.Utils.toLongList
import io.kotest.core.spec.style.StringSpec
import io.kotest.matchers.shouldBe

class Day1Test : StringSpec({
    val input = """
        1721
        979
        366
        299
        675
        1456
        """.toLongList()

    "part 1" {
        Day1.sum2To2020(input) shouldBe 514579
    }

    "part 2" {
        Day1.sum3To2020(input) shouldBe 241861950
    }
})

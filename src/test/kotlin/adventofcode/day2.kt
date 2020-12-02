package adventofcode

import adventofcode.Utils.toStringList
import io.kotest.core.spec.style.StringSpec
import io.kotest.matchers.shouldBe

class Day2Test : StringSpec({
    val input = """
        1-3 a: abcde
        1-3 b: cdefg
        2-9 c: ccccccccc
        """.toStringList()

    "part 1" {
        Day2.countLegalPasswords(input, Day2.sledRentalPlaceDownTheStreet) shouldBe 2
    }

    "part 2" {
        Day2.countLegalPasswords(input, Day2.officialTobogganCorporateAuthenticationSystem) shouldBe 1
    }
})
package adventofcode

import io.kotest.core.spec.style.StringSpec
import io.kotest.matchers.shouldBe
import java.io.File

class Day19Test : StringSpec({

    val input1 = File("src/test/resources/day19_1.txt").readText()
    val input2 = File("src/test/resources/day19_2.txt").readText()

//    "part 1_1" {
//        Day19.part1(input1) shouldBe 2
//    }

    "part 1_2" {
        Day19.part1(input2) shouldBe 2
    }
})

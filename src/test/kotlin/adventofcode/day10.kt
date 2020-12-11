package adventofcode

import io.kotest.core.spec.style.StringSpec
import io.kotest.matchers.shouldBe
import java.io.File

class Day10Test : StringSpec({

    val input1 = File("src/test/resources/day10_1.txt").readLines()
    val input2 = File("src/test/resources/day10_2.txt").readLines()

    "part 1 input 1"() {
        Day10.part1(input1) shouldBe 35
    }

    "part 1 input 2"() {
        Day10.part1(input2) shouldBe 220
    }

    "part 2 input 1"() {
        Day10.part2(input1) shouldBe 8
    }

    "part 2 input 2"() {
        Day10.part2(input2) shouldBe 19208
    }
})

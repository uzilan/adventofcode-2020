package adventofcode

import io.kotest.core.spec.style.StringSpec
import io.kotest.matchers.shouldBe
import java.io.File

class Day12Test : StringSpec({

    val input = File("src/test/resources/day12.txt").readLines()


    "part 1"() {
        Day12.part1(input) shouldBe 25
    }

//    "part 2"() {
//        Day12.part2(input) shouldBe 286
//    }
})
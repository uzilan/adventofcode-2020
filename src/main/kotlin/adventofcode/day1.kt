package adventofcode

import adventofcode.Utils.printResult
import java.io.File
import kotlin.time.ExperimentalTime

object Day1 {

    fun sum2To2020(input: List<Long>): Long {
        input.forEachIndexed { i, first ->
            input.drop(i + 1).forEach { second ->
                if (first + second == 2020L) return first * second
            }
        }
        return 0
    }

    fun sum3To2020(input: List<Long>): Long {
        input.forEachIndexed { i, first ->
            input.drop(i + 1).forEachIndexed { j, second ->
                input.drop(j + 1).forEach { third ->
                    if (first + second + third == 2020L) return first * second * third
                }
            }
        }
        return 0
    }

    @ExperimentalTime
    @JvmStatic
    fun main(args: Array<String>) {
        val input = File("src/main/resources/day1.txt").readLines()
            .map { it.toLong() }

        printResult("part 1") { sum2To2020(input) }
        printResult("part 2") { sum3To2020(input) }
    }
}
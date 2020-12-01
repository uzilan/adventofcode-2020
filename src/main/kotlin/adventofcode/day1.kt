package adventofcode

import java.io.File

object Day1 {
    fun sum2To2020(input: List<Long>): Long {
        input.forEach { first ->
            input.drop(1).forEach { second ->
                if (first + second == 2020L) return first * second
            }
        }
        return 0
    }

    fun sum3To2020(input: List<Long>): Long {
        input.forEach { first ->
            input.drop(1).forEach { second ->
                input.drop(2).forEach { third ->
                    if (first + second + third == 2020L) return first * second * third
                }
            }
        }
        return 0
    }

    @JvmStatic
    fun main(args: Array<String>) {
        val lines = File("src/main/resources/day1.txt").readLines()
        val input = lines.map { it.toLong() }
        val part1 = sum2To2020(input)
        println("part 1: $part1")
        val part2 = sum3To2020(input)
        println("part 2: $part2")
    }
}
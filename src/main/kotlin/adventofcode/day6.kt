package adventofcode

import adventofcode.Utils.printResult
import java.io.File
import kotlin.time.ExperimentalTime

object Day6 {
    fun part1(input: String): Int {
        return input.split("\n\n")
            .map {
                it.replace("\n", "")
                    .toSet()
                    .count()
            }.sum()
    }

    fun part2(input: String): Int {
        return input.split("\n\n")
            .map { it.split("\n") }
            .map { group ->
                group[0].fold("") { acc, s ->
                    if (group.all { it.contains(s) }) acc + s
                    else acc
                }.count()
            }.sum()
    }

    @ExperimentalTime
    @JvmStatic
    fun main(args: Array<String>) {
        val input = File("src/main/resources/day6.txt").readText()

        printResult("part 1") { part1(input) }
        printResult("part 2") { part2(input) }
    }
}
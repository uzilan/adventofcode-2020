package adventofcode

import adventofcode.Utils.printResult
import java.io.File
import kotlin.time.ExperimentalTime

object Day10 {

    fun part1(input: List<String>): Int {
        val sorted = input.map { it.toInt() }.sorted()
        val list = listOf(0) + sorted + (sorted.last() + 3)

        return mapDiffs(list)
            .values
            .map { it.size }
            .reduce(Int::times)
    }

    fun part2(input: List<String>): Long {
        val sortedInput = input.map { it.toInt() }.sorted()
        val finalIntList = listOf(0) + sortedInput + (sortedInput.last() + 3)

        return (1..finalIntList.last())
            .fold(listOf(1L)) { acc, i ->
                acc + (i - 3 until i)
                    .filter { it in finalIntList }
                    .sumOf { acc[it] }
            }.last()
    }

    private fun mapDiffs(list: List<Int>) = list.dropLast(1)
        .mapIndexed { index, element -> Pair(element, list[index + 1]) }
        .map { it.second - it.first }
        .groupBy { it }

    @ExperimentalTime
    @JvmStatic
    fun main(args: Array<String>) {
        val input = File("src/main/resources/day10.txt").readLines()

        printResult("part 1") { part1(input) }
        printResult("part 2") { part2(input) }
    }
}
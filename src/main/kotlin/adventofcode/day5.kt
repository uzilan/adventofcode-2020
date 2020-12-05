package adventofcode

import adventofcode.Utils.printResult
import java.io.File
import kotlin.time.ExperimentalTime

object Day5 {

    fun part1(input: List<String>): Int? {
        val ids = getAllIds(input)
        return ids.maxOrNull()
    }

    fun part2(input: List<String>): Int {
        val ids = getAllIds(input)
        val interval = ids.minOrNull()!!..ids.maxOrNull()!!
        val id = interval - ids
        return id.first()
    }

    private fun getAllIds(input: List<String>): List<Int> {
        return input.map {
            val line = findLineOrSeat(it.take(7), 127)
            val seat = findLineOrSeat(it.drop(7), 7)
            line * 8 + seat
        }
    }

    private data class LowHigh(val low: Int, val high: Int) {
        private val half = (high - low) / 2 + low
        fun lowerHalf() = LowHigh(low, half)
        fun upperHalf() = LowHigh(half + 1, high)
    }

    private fun findLineOrSeat(input: String, max: Int): Int {
        val result = input.fold(LowHigh(0, max)) { acc, char ->
            when (char) {
                'F', 'L' -> acc.lowerHalf()
                else -> acc.upperHalf()
            }
        }
        return result.high
    }

    @ExperimentalTime
    @JvmStatic
    fun main(args: Array<String>) {
        val input = File("src/main/resources/day5.txt").readLines()
        printResult("part 1") { part1(input) }
        printResult("part 2") { part2(input) }
    }
}
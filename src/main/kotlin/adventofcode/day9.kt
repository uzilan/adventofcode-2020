package adventofcode

import adventofcode.Utils.printResult
import java.io.File
import kotlin.time.ExperimentalTime

object Day9 {

    fun part1(input: List<String>, preambleLength: Int, previousLength: Int): Long {
        val numbers = input.map { it.toLong() }
        val numbersToCheck = numbers.drop(preambleLength)

        numbersToCheck.forEachIndexed { index, num ->
            val indexInNumbers = preambleLength + index
            val previousNumbers = numbers.subList(indexInNumbers - previousLength, indexInNumbers)

            if (!sumFound(num, previousNumbers)) return num
        }
        return 0
    }

    fun part2(input: List<String>, preambleLength: Int, previousLength: Int): Long {
        val numbers = input.map { it.toLong() }
        val invalid = part1(input, preambleLength, previousLength)
        numbers.forEachIndexed { index, num ->
            val sumOrNot = tryToSum(invalid, num, numbers.drop(index + 1))
            if (sumOrNot.first) return sumOrNot.second
        }
        return 0
    }

    private fun sumFound(num: Long, numbers: List<Long>): Boolean {
        numbers.forEachIndexed { index, first ->
            numbers.drop(index).forEach { second ->
                if (first + second == num) return true
            }
        }
        return false
    }

    private fun tryToSum(invalid: Long, initial: Long, numbers: List<Long>): Pair<Boolean, Long> {
        numbers.foldIndexed(initial) { index, acc, curr ->
            val currentSum = acc + curr
            when {
                currentSum == invalid -> return Pair(true, minMax(listOf(initial) + numbers.take(index + 1)))
                currentSum > invalid -> return Pair(false, -1)
                else -> currentSum
            }
        }
        return Pair(false, -1)
    }

    private fun minMax(numbers: List<Long>): Long {
        return numbers.minOrNull()!! + numbers.maxOrNull()!!
    }

    @ExperimentalTime
    @JvmStatic
    fun main(args: Array<String>) {
        val input = File("src/main/resources/day9.txt").readLines()

        printResult("part 1") { part1(input, 25, 25) }
        printResult("part 2") { part2(input, 25, 25) }
    }
}
package adventofcode

import java.io.File
import kotlin.math.round
import kotlin.time.ExperimentalTime

object Day13 {

    fun part1(input: List<String>): Double {
        val earliestTimestamp = input[0].toDouble()
        val busIds = input[1].split(",").filterNot { it == "x" }.map { it.toDouble() }

        val earliestBusAndTime = busIds
            .map { Pair(it, round(earliestTimestamp / it) * it) }
            .filter { it.second >= earliestTimestamp }
            .minByOrNull { it.second }!!

        val busId = earliestBusAndTime.first
        val waitingTime = earliestBusAndTime.second - earliestTimestamp
        return busId * waitingTime
    }

    @ExperimentalTime
    @JvmStatic
    fun main(args: Array<String>) {
        val input = File("src/main/resources/day13.txt").readLines()

        Utils.printResult("part 1") { part1(input) }
    }
}
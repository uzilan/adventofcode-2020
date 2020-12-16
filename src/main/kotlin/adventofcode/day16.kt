package adventofcode

import java.io.File
import kotlin.time.ExperimentalTime

object Day16 {

    fun part1(input: String): Int {
        val split = input.split("\n\n")
        val fieldIntervals = split[0].split("\n")
            .map { it.split(" or ") }
            .flatMap {
                val from = it[0].split(" ").last()
                val to = it.last()
                listOf(from, to)
            }
            .map { it.split("-") }
            .map { it[0].toInt()..it[1].toInt() }

        val ticketValues = split[2]
            .split("\n")
            .drop(1)
            .flatMap { it.split(",") }
            .map { it.toInt() }

        val invalidValues = ticketValues
            .filter { ticketValue ->
                fieldIntervals.none { interval ->
                    interval.contains(ticketValue)
                }
            }

        return invalidValues.sum()
    }

    @ExperimentalTime
    @JvmStatic
    fun main(args: Array<String>) {
        val input = File("src/main/resources/day16.txt").readText()

        Utils.printResult("part 1") { part1(input) }
    }
}
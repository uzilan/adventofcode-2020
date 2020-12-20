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

    data class Field(val name: String, val intervals: List<IntRange>) {
        fun isValid(num: Int) = intervals.any { it.contains(num) }
    }

    fun part2(input: String): Int {
        val split = input.split("\n\n")
        val fields = split[0]
            .split("\n")
            .map { row ->
                val rowSplit = row.split(" ")
                val range2 = rowSplit.last().toRange()
                val range1 = rowSplit[rowSplit.size - 3].toRange()
                val name = rowSplit.dropLast(3).joinToString(" ").dropLast(1)
                Field(name, listOf(range1, range2))
            }
        val allIntervals = fields.flatMap { it.intervals }

        val myTicket = parseTickets(split[1])[0]
        val nearbyTickets = parseTickets(split[2])
        val allNearbyTicketValues = nearbyTickets.flatten()
        val invalidTicketValues = allNearbyTicketValues.filter { ticketValue ->
            allIntervals.none { interval ->
                interval.contains(ticketValue)
            }
        }
        val validTickets = nearbyTickets.filterNot { ticket ->
            ticket.any { ticketValue -> invalidTicketValues.contains(ticketValue) }
        }

        val fieldMapping = (myTicket.indices).map { index ->
            val f = fields.filter { field ->
                validTickets
                    .map { ticket -> ticket[index] }
                    .all { num -> field.isValid(num) }
            }
            index to f
        }.toMap()

        val fieldCountMap = fieldMapping.values.flatten().groupingBy { it }.eachCount()

        val fieldClassMap = fieldMapping.map { entry ->
            if (entry.value.size > 1) {
                fieldCountMap.entries.find { fieldEntry -> entry.value.contains(fieldEntry.key) && fieldEntry.value == 1 }?.key
            } else entry.value.first()
        }

        val departureFieldClasses = fieldClassMap.filter {
            it!!.name.contains("departure")
        }

        return 0
    }

    private fun parseTickets(input: String): List<List<Int>> {
        return input.split("\n")
            .drop(1)
            .map { row ->
                row.split(",")
                    .map { it.toInt() }
            }
    }

    @ExperimentalTime
    @JvmStatic
    fun main(args: Array<String>) {
        val input = File("src/main/resources/day16.txt").readText()

        //Utils.printResult("part 1") { part1(input) }
        Utils.printResult("part 2") { part2(input) }
    }
}

private fun String.toRange(): IntRange {
    return this.split("-").run {
        this[0].toInt()..this[1].toInt()
    }
}

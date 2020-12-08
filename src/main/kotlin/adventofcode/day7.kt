package adventofcode

import adventofcode.Utils.printResult
import java.io.File
import kotlin.time.ExperimentalTime

object Day7 {

    data class Bag(val description: String, val count: Int)

    fun part1(input: List<String>): Int {
        val split = splitBags(input)
        return findOuterBagsContaining(split, "shiny gold").size
    }

    fun part2(input: List<String>): Int {
        val split = splitBags(input)
        return sumBags(split, "shiny gold")
    }

    private fun splitBags(input: List<String>): Map<String, List<Bag>> {
        return input.map { line ->
            val row = line.split(" bags contain ")
            row[0] to row[1].split(",").mapNotNull { it.toBag() }
        }.toMap()
    }

    private fun findOuterBagsContaining(bags: Map<String, List<Bag>>, bagDescription: String): List<String> {
        val outerBags = bags.entries
            .filter { entry -> containsBag(entry.value, bagDescription) }
            .map { it.key }

        val moreOuterBags = outerBags
            .flatMap { findOuterBagsContaining(bags, it) }

        return (outerBags + moreOuterBags).distinct()
    }

    private fun containsBag(list: List<Bag>, bagDescription: String): Boolean {
        return list.any { it.description == bagDescription }
    }

    private fun String.toBag(): Bag? {
        if (this.contains("no other bags")) return null
        val trimmed = this
            .replace(".", "")
            .replace("bags", "")
            .replace("bag", "")
            .trim()
        val split = trimmed.split(" ")
        return Bag("${split[1]} ${split[2]}", split[0].toInt())
    }

    private fun sumBags(bags: Map<String, List<Bag>>, bagDescription: String): Int {
        val outer = bags[bagDescription] ?: return 0
        return outer.sumBy { it.count + it.count * sumBags(bags, it.description) }
    }

    @ExperimentalTime
    @JvmStatic
    fun main(args: Array<String>) {
        val input = File("src/main/resources/day7.txt").readLines()

        printResult("part 1") { part1(input) }
        printResult("part 2") { part2(input) }
    }
}
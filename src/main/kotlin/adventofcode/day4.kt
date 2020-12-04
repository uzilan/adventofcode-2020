package adventofcode

import adventofcode.Utils.printResult
import java.io.File
import kotlin.time.ExperimentalTime

object Day4 {

    private enum class EyeColor { amb, blu, brn, gry, grn, hzl, oth }

    private enum class Field(val description: String, val valid: (String) -> Boolean) {
        byr("Birth Year", { it.between(1920, 2002) }),
        iyr("Issue Year", { it.between(2010, 2020) }),
        eyr("Expiration Year", { it.between(2020, 2030) }),
        hgt("Height", { it.validHeight() }),
        hcl("Hair Color", { it.validHairColor() }),
        ecl("Eye Color", { it.validEyeColor() }),
        pid("Passport ID", { it.validPassportId() }),
        cid("Country ID", { true }),
    }

    private fun String.between(min: Int, max: Int): Boolean {
        this.toIntOrNull()?.apply {
            return this in min..max
        }
        return false
    }

    private fun String.validHeight(): Boolean {
        val length = this.dropLast(2)
        val unit = this.takeLast(2)
        return when (unit) {
            "cm" -> length.between(150, 193)
            "in" -> length.between(59, 76)
            else -> false
        }
    }

    private fun String.validHairColor(): Boolean {
        return "#[0-9abcdef]{6}".toRegex().matches(this)
    }

    private fun String.validEyeColor(): Boolean {
        return EyeColor.values().map { it.toString() }.contains(this)
    }

    private fun String.validPassportId(): Boolean {
        if (this.length != 9) return false
        this.toIntOrNull()?.apply {
            return true
        }
        return false
    }

    private val requiredFields = Field.values()
        .filterNot { it == Field.cid }
        .map { it.toString() }

    fun part1(input: String): Int {
        val passports = parseInput(input)
        return passports.count { passportContainsRequiredFields(it) }
    }

    fun part2(input: String): Int {
        val passports = parseInput(input)
        return passports.count { passportContainsRequiredFields(it) && passportIsValid(it) }
    }

    private fun passportContainsRequiredFields(passport: Map<String, String>): Boolean {
        return passport.keys.containsAll(requiredFields)
    }

    private fun passportIsValid(passport: Map<String, String>): Boolean {
        return passport.all { (key, value) ->
            Field.valueOf(key).valid(value)
        }
    }

    private fun parseInput(input: String): List<Map<String, String>> {
        return input
            .split("\n\n")
            .map { it.replace("\n", " ") }
            .map { it.split(" ") }
            .map { row ->
                row.map { it.split(":") }
                    .map { Pair(it[0], it[1]) }
                    .toMap()
            }
    }

    @ExperimentalTime
    @JvmStatic
    fun main(args: Array<String>) {
        val input = File("src/main/resources/day4.txt").readText()
        printResult("part 1") { part1(input) }
        printResult("part 2") { part2(input) }
    }
}



package adventofcode

import adventofcode.Utils.printResult
import java.io.File
import kotlin.time.ExperimentalTime

object Day2 {

    fun countLegalPasswords(input: List<String>, legalStrategy: LegalStrategy): Int {
        return input.map { parse(it) }
            .count { legalStrategy.isLegal(it) }
    }

    private fun parse(line: String): Row {
        val split = line.split(" ")
        val numbers = split[0].split("-")
        return Row(
            firstNumber = numbers[0].toInt(),
            secondNumber = numbers[1].toInt(),
            letter = split[1].first(),
            password = split[2]
        )
    }

    interface LegalStrategy {
        fun isLegal(row: Row): Boolean
    }

    class SledRentalPlaceDownTheStreet : LegalStrategy {
        override fun isLegal(row: Row): Boolean {
            val count = row.password.count { it == row.letter }
            return count >= row.firstNumber && count <= row.secondNumber
        }
    }

    class OfficialTobogganCorporateAuthenticationSystem : LegalStrategy {
        override fun isLegal(row: Row): Boolean {
            val inFirstPosition = row.password[row.firstNumber - 1] == row.letter
            val inSecondPosition = row.password[row.secondNumber - 1] == row.letter
            return inFirstPosition xor inSecondPosition
        }
    }

    @ExperimentalTime
    @JvmStatic
    fun main(args: Array<String>) {
        val input = File("src/main/resources/day2.txt")
            .readLines()

        printResult("part 1") { countLegalPasswords(input, SledRentalPlaceDownTheStreet()) }
        printResult("part 2") { countLegalPasswords(input, OfficialTobogganCorporateAuthenticationSystem()) }
    }
}

data class Row(val firstNumber: Int, val secondNumber: Int, val letter: Char, val password: String)

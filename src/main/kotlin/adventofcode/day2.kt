package adventofcode

import adventofcode.Day2.LegalStrategy
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

    fun interface LegalStrategy {
        fun isLegal(row: Row): Boolean
    }

    val sledRentalPlaceDownTheStreet = LegalStrategy { row ->
        val count = row.password.count { it == row.letter }
        count >= row.firstNumber && count <= row.secondNumber
    }

    val officialTobogganCorporateAuthenticationSystem = LegalStrategy { row ->
        val inFirstPosition = row.password[row.firstNumber - 1] == row.letter
        val inSecondPosition = row.password[row.secondNumber - 1] == row.letter
        inFirstPosition xor inSecondPosition
    }

    @ExperimentalTime
    @JvmStatic
    fun main(args: Array<String>) {
        val input = File("src/main/resources/day2.txt").readLines()

        printResult("part 1") { countLegalPasswords(input, sledRentalPlaceDownTheStreet) }
        printResult("part 2") { countLegalPasswords(input, officialTobogganCorporateAuthenticationSystem) }
    }
}

data class Row(val firstNumber: Int, val secondNumber: Int, val letter: Char, val password: String)

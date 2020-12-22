package adventofcode

import java.io.File
import kotlin.time.ExperimentalTime

object Day22 {

    fun part1(input: String): Int {
        val split = input.split("\n\n")
        val cards1 = split[0].split("\n").drop(1).map { it.toInt() }
        val cards2 = split[1].split("\n").drop(1).map { it.toInt() }

        val winnerHand = round(1, cards1, cards2)

        return winnerHand.reversed().foldIndexed(0) { index, acc, current ->
            acc + current * (index + 1)
        }
    }

    private tailrec fun round(number: Int, cards1: List<Int>, cards2: List<Int>): List<Int> {

        return when {
            cards1.isEmpty() -> {
                printPostGameResult(cards1, cards2)
                cards2
            }
            cards2.isEmpty() -> {
                printPostGameResult(cards1, cards2)
                cards1
            }
            else -> {
                val first1 = cards1.first()
                val first2 = cards2.first()

                println(
                    """
                -- Round $number --
                Player 1's deck: ${cards1.str()}
                Player 2's deck: ${cards2.str()}
                Player 1 plays: $first1
                Player 2 plays: $first2
                """.trimIndent()
                )
                return when {
                    cards1.first() > cards2.first() -> {
                        println("Player 1 wins the round!\n")
                        round(number + 1, cards1.drop(1) + first1 + first2, cards2.drop(1))
                    }
                    else -> {
                        println("Player 2 wins the round!\n")
                        round(number + 1, cards1.drop(1), cards2.drop(1) + first2 + first1)
                    }
                }
            }
        }
    }

    private fun printPostGameResult(cards1: List<Int>, cards2: List<Int>) {
        println(
            """
        == Post-game results ==
        Player 1's deck: ${cards1.str()}
        Player 2's deck: ${cards2.str()}""".trimIndent()
        )
        println()
    }

    private fun List<Int>.str() = this.joinToString(", ")

    @ExperimentalTime
    @JvmStatic
    fun main(args: Array<String>) {
        val input = File("src/main/resources/day22.txt").readText()

        Utils.printResult("part 1") { part1(input) }
    }
}
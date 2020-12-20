package adventofcode

typealias Pointers = List<Int>

object Day19 {

    abstract class Rule(open val index: Int)
    data class MatchRule(override val index: Int, val letter: Char) : Rule(index) {
        override fun toString(): String {
            return "$index: should begin with $letter"
        }
    }

    data class PointerRule(override val index: Int, val pointers: List<Pointers>) : Rule(index){
        override fun toString(): String {
            return "$index: pointers: $pointers"
        }
    }

    fun part1(input: String): Int {

        val split = input.split("\n\n")
        val rules = split[0]
            .split("\n")
            .map {
                when {
                    it.contains("\"") -> it.toMatchRule()
                    it.contains("|") -> it.toMultiPointerRule()
                    else -> it.toSinglePointerRule()
                }
            }

        val messages = split[1].split("\n")

        val map = messages.map { it to followRules(rules, rules[0], it, 0) }


        return 0
    }

    private fun followRules(rules: List<Rule>, current: Rule, input: String, tabIndex: Int): Boolean {
        val tabs = "\t".repeat(tabIndex)
        println("${tabs}testing rule $current on input $input...")
        val result = when {
            input.isEmpty() -> true
            current is MatchRule -> input[0] == current.letter
            current is PointerRule -> {
                current.pointers.any { pointer ->
                    pointer.map { rules[it] }
                        .mapIndexed { index, rule ->
                            index to rule
                        }
                        .all {
                            val (index, rule) = it
                            followRules(rules, rule, input.drop(index), tabIndex + 1)
                        }
                }
            }
            else -> false
        }
        println("$tabs$result")
        return result
    }

    private fun String.toMatchRule(): MatchRule {
        val split = this.split(" ")
        val index = split[0].dropLast(1).toInt()
        val letter = split[1].replace("\"", "")[0]
        return MatchRule(index, letter)
    }

    private fun String.toSinglePointerRule(): PointerRule {
        val split = this.split(" ")
        val index = split[0].dropLast(1).toInt()
        val pointers = split.drop(1).toPointers()
        return PointerRule(index, listOf(pointers))
    }

    private fun String.toMultiPointerRule(): PointerRule {
        val barSplit = this.split("|")
        val leftSplit = barSplit[0].trim().split(" ")
        val rightSplit = barSplit[1].trim().split(" ")
        val index = leftSplit[0].dropLast(1).toInt()
        val leftPointers = leftSplit.drop(1).toPointers()
        val rightPointers = rightSplit.toPointers()
        return PointerRule(index, listOf(leftPointers, rightPointers))
    }

    private fun List<String>.toPointers(): Pointers {
        return this.map { it.toInt() }
    }
}


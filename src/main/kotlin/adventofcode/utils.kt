package adventofcode

object Utils {

    fun String.toIntList(): List<Long> {
        return this.split("\n")
                .drop(1)
                .dropLast(1)
                .map {
                    it.trim().toLong()
                }
    }
}
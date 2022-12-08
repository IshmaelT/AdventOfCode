import java.io.FileReader

fun main() {

    val signal = FileReader("./day06/input.txt")
        .readText()
        .lines()
        .first { it.isNotBlank() }

    var count = 0
    signal.forEachIndexed { index, c ->
        val size = signal.subSequence(index, index + 14)
            .also { println(it) }
            .toList()
            .distinct()
            .size
        if (size == 14) {
            println(index + 14)
            return
        }
    }

    println(count)

}

private fun partOne(signal: String) {
    var count = 0
    signal.forEachIndexed { index, c ->
        val size = signal.subSequence(index, index + 4)
            .also { println(it) }
            .toList()
            .distinct()
            .size
        if (size == 4) {
            println(index + 4)
            return
        }
    }

    println(count)
}
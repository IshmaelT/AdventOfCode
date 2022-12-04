import java.io.FileReader

fun main() {

    val assignmentPairs = FileReader("./day04/input.txt")
        .readText()
        .lines()

    operator fun String.component1() = split("-")[0]
    operator fun String.component2() = split("-")[1]

    var count = 0
    assignmentPairs.forEach {
        if (it.isBlank()) return@forEach
        val (a, b) = it.split(',')
        val (aInit, aFinal) = a
        val (bInit, bFinal) = b
        val firstElf = aInit.toInt()..aFinal.toInt()
        val secondElf = bInit.toInt()..bFinal.toInt()
        val intersection = firstElf intersect secondElf
        if (intersection.isNotEmpty()) {
            println(it)
            println("${intersection.first()}-${intersection.last()}")
            count++
        }
    }
    println("Pairs with overlap = $count")
}

private fun partOne(assignmentPairs: List<String>) {
    var count = 0
    assignmentPairs.forEach {
        if (it.isBlank()) return@forEach
        val (a, b) = it.split(',')
        val (aInit, aFinal) = a.split("-")
        val (bInit, bFinal) = b.split("-")
        val firstElf = (aInit.toInt()..aFinal.toInt())
        val secondElf = (bInit.toInt()..bFinal.toInt())
        val intersection = firstElf intersect secondElf
        if (intersection.size == firstElf.toList().size || intersection.size == secondElf.toList().size) {
            println(it)
            println("${intersection.first()}-${intersection.last()}")
            count++
        }
    }
    println("Pairs with fully contained range = $count")
}
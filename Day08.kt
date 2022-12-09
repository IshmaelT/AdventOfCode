import java.io.FileReader

fun main() {
    val lines = FileReader("./day08/input.txt")
        .readText()
        .lines()
        .dropLast(1)

    val rows = mutableListOf<List<Int>>()
    for (line in lines) {
        rows.add(line.map { "$it".toInt() })
    }

    val unique = mutableSetOf<Pair<Int, Int>>()
    for ((rowNumber, row) in rows.withIndex()) {
        for ((columnNumber, number) in row.withIndex()) {
            if (rowNumber in 1 until (rows.size - 1) &&
                columnNumber in 1 until (row.size - 1)
            ) {
                val (left, _) = row.withIndex().partition { it.index < columnNumber }
                val (right, _) = row.withIndex().partition { it.index > columnNumber }

                val column = rows.map { it[columnNumber] }

                val (top, _) = column.withIndex().partition { it.index < rowNumber }
                val (bottom, _) = column.withIndex().partition { it.index > rowNumber }

                //println("Row $rowNumber && column $columnNumber = left: ${top.map { it.value }} && right: ${bottom.map { it.value }}")

                if (
                    left.maxOf { it.value } < number ||
                    right.maxOf { it.value } < number ||
                    top.maxOf { it.value } < number ||
                    bottom.maxOf { it.value } < number
                ) {
                    unique.add(rowNumber to columnNumber)
                }
            }
        }
    }

    println(unique)
    println(unique.size + (2 * rows.size) + (2 * (rows.first().size - 2)) )
}
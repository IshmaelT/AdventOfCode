import java.io.FileReader
fun main() {
    val input = FileReader("./day09/input.txt")
        .readText()
        .lines()
        .dropLast(1)

    data class Point(
        var x: Int = 0,
        var y: Int = 0,
        val tail: Point? = null,
        val char: String = " . "
    )

    fun Point.follow(): Point {
        tail ?: return this
        when {
            x - tail.x > 1 -> {
                if (y - tail.y > 0) {
                    tail.y += 1
                } else if (tail.y - y > 0) {
                    tail.y -= 1
                }
                tail.x += 1
            }

            tail.x - x > 1 -> {
                if (y - tail.y > 0) {
                    tail.y += 1
                } else if (tail.y - y > 0) {
                    tail.y -= 1
                }
                tail.x -= 1
            }

            y - tail.y > 1 -> {
                if (x - tail.x > 0) {
                    tail.x += 1
                } else if (tail.x - x > 0) {
                    tail.x -= 1
                }
                tail.y += 1
            }

            tail.y - y > 1 -> {
                if (x - tail.x > 0) {
                    tail.x += 1
                } else if (tail.x - x > 0) {
                    tail.x -= 1
                }
                tail.y -= 1
            }
        }
        return tail
    }

    val tail9th = Point(char = " 9 ")
    val tail8th = Point(tail = tail9th, char = " 8 ")
    val tail7th = Point(tail = tail8th, char = " 7 ")
    val tail6th = Point(tail = tail7th, char = " 6 ")
    val tail5th = Point(tail = tail6th, char = " 5 ")
    val tail4th = Point(tail = tail5th, char = " 4 ")
    val tail3rd = Point(tail = tail4th, char = " 3 ")
    val tail2nd = Point(tail = tail3rd, char = " 2 ")
    val tail1st = Point(tail = tail2nd, char = " 1 ")
    val head = Point(tail = tail1st, char = " H ")

    val uniqueTailPath = mutableSetOf<Point>()

    operator fun String.component1() = first()
    operator fun String.component2() = substringAfter(" ").toInt()

    fun follow() {
        val tail = head.follow() // 1
            .follow() // 2
            .follow() // 3
            .follow() // 4
            .follow() // 5
            .follow() // 6
            .follow() // 7
            .follow() // 8
            .follow() // 9
        uniqueTailPath.add(tail.copy())
    }

    input.forEach { (dir, steps) ->
        when (dir) {
            'R' -> {
                repeat(steps) {
                    head.x += 1
                    follow()
                }
            }

            'L' -> {
                repeat(steps) {
                    head.x -= 1
                    follow()
                }
            }

            'U' -> {
                repeat(steps) {
                    head.y += 1
                    follow()
                }
            }

            'D' -> {
                repeat(steps) {
                    head.y -= 1
                    follow()
                }
            }
        }
    }

    println(uniqueTailPath.count())
}
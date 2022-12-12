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

    fun Point.shiftY(tail: Point) {
        if (y - tail.y > 0) {
            tail.y += 1
        } else if (tail.y - y > 0) {
            tail.y -= 1
        }
    }

    fun Point.shiftX(tail: Point) {
        if (x - tail.x > 0) {
            tail.x += 1
        } else if (tail.x - x > 0) {
            tail.x -= 1
        }
    }

    fun Point.follow(): Point {
        tail ?: return this
        if (x - tail.x > 1) {
            shiftY(tail)
            tail.x += 1
        }
        if (tail.x - x > 1) {
            shiftY(tail)
            tail.x -= 1
        }
        if (y - tail.y > 1) {
            shiftX(tail)
            tail.y += 1
        }
        if (tail.y - y > 1) {
            shiftX(tail)
            tail.y -= 1
        }
        return tail.follow()
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

    fun follow() = head.follow().also { uniqueTailPath.add(it.copy()) }

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
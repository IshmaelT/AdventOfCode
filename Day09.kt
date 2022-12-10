import java.io.FileReader
import kotlin.math.abs

fun main() {
    val input = FileReader("./day09/input.txt")
        .readText()
        .lines()
        .dropLast(1)

    abstract class Point
    data class Head(var x: Int = 0, var y: Int = 0) : Point()
    data class Tail(var x: Int = 0, var y: Int = 0) : Point()

    val head = Head()
    val tail = Tail()

    val uniqueTailPath = mutableListOf<Tail>()

    operator fun String.component1() = first()
    operator fun String.component2() = substringAfter(" ").toInt()

    fun tryFollow() {
        when {
            (head.x > tail.x + 1) && abs(head.y - tail.y) > 0 -> {
                tail.y = head.y
                tail.x += 1
            }

            head.x > tail.x + 1 -> tail.x += 1

            (head.x < tail.x - 1) && abs(head.y - tail.y) > 0 -> {
                tail.y = head.y
                tail.x -= 1
            }

            head.x < tail.x - 1 -> tail.x -= 1

            (head.y > tail.y + 1) && abs(head.x - tail.x) > 0 -> {
                tail.x = head.x
                tail.y += 1
            }

            head.y > tail.y + 1 -> tail.y += 1

            (head.y < tail.y - 1) && abs(head.x - tail.x) > 0 -> {
                tail.x = head.x
                tail.y -= 1
            }

            head.y < tail.y - 1 -> tail.y -= 1
        }
    }

    input.forEach { (dir, steps) ->
        when (dir) {
            'R' -> {
                repeat(steps) {
                    head.x += 1
                    tryFollow()
                    uniqueTailPath.add(tail.copy())
                }
            }

            'L' -> {
                repeat(steps) {
                    head.x -= 1
                    tryFollow()
                    uniqueTailPath.add(tail.copy())
                }
            }

            'U' -> {
                repeat(steps) {
                    head.y += 1
                    tryFollow()
                    uniqueTailPath.add(tail.copy())
                }
            }

            'D' -> {
                repeat(steps) {
                    head.y -= 1
                    tryFollow()
                    uniqueTailPath.add(tail.copy())
                }
            }
        }
    }

    uniqueTailPath.onEach { println(it) }
    println(uniqueTailPath.distinct().count())
}
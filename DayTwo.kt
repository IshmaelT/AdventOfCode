import java.io.BufferedReader
import java.io.FileReader
import kotlin.streams.toList

fun main() {

    val file = FileReader("./daytwo/input.txt")
    val br = BufferedReader(file)

    val scores = br.lines().toList().map {

        val opponentChoice = it.first()
        val opponentChoiceScore = when(opponentChoice) {
            'A' -> 1
            'C' -> 3
            else -> 2
        }

        val choice = it.last()
        val choiceScore = when(choice) {
            'X' -> 1
            'Z' -> 3
            else -> 2
        }

        val winLoseOrDraw = when {
            opponentChoiceScore == choiceScore -> 3
            opponentChoiceScore == 3 && choiceScore == 1 -> 6
            opponentChoiceScore == 2 && choiceScore == 3 -> 6
            opponentChoiceScore == 1 && choiceScore == 2 -> 6
            else -> 0
        }
        val total = choiceScore + winLoseOrDraw
        println("$it = ${total}" )
        total
    }

    println("Total scores = ${scores.sum()}")

}
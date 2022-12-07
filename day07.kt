import java.io.FileReader
import java.util.TreeMap

fun main() {

    val lines = FileReader("./day07/input.txt")
        .readText()
        .lines()
        .filter { it.isNotBlank() }



    val dirMap = HashMap<Int, List<String>>()


//    val lines = ("\$ cd /\n" +
//            "\$ ls\n" +
//            "dir a\n" +
//            "14848514 b.txt\n" +
//            "8504156 c.dat\n" +
//            "dir d\n" +
//            "\$ cd a\n" +
//            "\$ ls\n" +
//            "dir e\n" +
//            "29116 f\n" +
//            "2557 g\n" +
//            "62596 h.lst\n" +
//            "\$ cd e\n" +
//            "\$ ls\n" +
//            "584 i\n" +
//            "\$ cd ..\n" +
//            "\$ cd ..\n" +
//            "\$ cd d\n" +
//            "\$ ls\n" +
//            "4060174 j\n" +
//            "8033020 d.log\n" +
//            "5626152 d.ext\n" +
//            "7214296 k")
//                .split("\n")

    lines.forEachIndexed { index, line ->
        if ("$ cd " in line &&
            "$ ls" == lines[index + 1]
        ) {
            //println(line)
            //println(lines[index + 1])
            var initial = index + 2
            val listOfFiles = mutableListOf<String>()
            while (initial < lines.size && "$" !in lines[initial]) {
                //println(lines[initial])
                listOfFiles.add(lines[initial])
                initial += 1
            }
            dirMap[index] = listOfFiles
            //println()
        }
    }

//    dirMap.onEach {
//        println("${it.key} = ${it.value}")
//    }

    //println(getSum(dirMap, "\$ cd /"))

    //dirMap.onEach { println("${it.key.split(" ").last()} = ${getSum(dirMap, "$ cd ${it.key.split(" ").last()}")}") }
    println(
        dirMap
            .map {
                val perKey = getSum(dirMap,  lines, it.key)
                //val result = if (perKey <= 100000) perKey else 0
                //println("${it.key} = $result")
                lines[it.key] to perKey
            }
            .sortedBy { it.second }
            .also { println(it.count()) }
            .onEach { println(it) }
            .sumOf { it.second }
    )

//    dirMap["\$ cd /"]?.forEach { file ->
//        val sum = mutableListOf<Int>()
//        if ("dir" in file) {
//            dirMap[file]
//        } else {
//            sum.add(file.split(" ").first().toInt())
//        }
//        sum.sum()
//    }

}

fun getSum(map: HashMap<Int, List<String>>, lines: List<String>,key: Int): Int {
    val sum = mutableListOf<Int>()
    map[key]?.forEach { file ->
        if ("dir" in file) {
            val subList = lines.subList(key + 1,lines.lastIndex)
            val newKey =  key + subList.indexOfFirst { it == ("$ cd ${file.split(" ").last()}") }
            val fileTot = getSum(map, lines, newKey)
            sum.add(fileTot)
            //println("$file = $fileTot")

        } else {
            val value = file.split(" ").first { it.isNotEmpty() && it.all { word -> word.isDigit() } }//println("$file = $value")
            sum.add(value.toInt())
        }
    }
    return sum.sum()
}


//fun HashMap<String, List<String>>.getTotal(key: String): Int {
//    val sum = mutableListOf<Int>()
//    forEach { file ->
//        if ("dir" in file) {
//            sum.add(get)
//        } else {
//            sum.add(file.split(" ").first().toInt())
//        }
//    }
//    return sum.sum()
//}


package Day1

import org.utils.*

val hashMap = linkedMapOf(
    "one" to "1",
    "two" to "2",
    "three" to "3",
    "four" to "4",
    "five" to "5",
    "six" to "6",
    "seven" to "7",
    "eight" to "8",
    "nine" to "9",
                         )

fun getAllSubstrings(input: String): List<String> {
    val substrings = mutableListOf<String>()
    for (i in input.indices) {
        for (j in i + 1..input.length) {
            substrings.add(input.substring(i, j))
        }
    }
    return substrings
}

fun getAllSubstringsFromEnd(input: String): List<String> {
    val substrings = mutableListOf<String>()
    for (i in input.indices.reversed()) {
        for (j in input.length downTo i + 1) {
            substrings.add(input.substring(i, j))
        }
    }
    return substrings
}

fun String.findFirst(): String {
    getAllSubstrings(this).forEach {
        if (hashMap.containsKey(it)) {
            return hashMap[it]!!
        }
        if (it.toIntOrNull() != null) {
            return it
        }
    }
    return 0.toString()
}

fun String.findLast(): String {
    getAllSubstringsFromEnd(this).forEach {
        if (hashMap.containsKey(it)) {
            return hashMap[it]!!
        }
        if (it.toIntOrNull() != null) {
            return it
        }
    }
    return "0"
}

fun main() {
    bestSolution()
    originalSolution()

}

// best solution that I was able to find online
fun bestSolution() {
    val digits = List(10) { "${it}"}
    val words = listOf("one", "two", "three", "four", "five", "six", "seven", "eight", "nine")
    val revWords = words.map { it.reversed() }
    println(revWords)
    fun String.digitWordToInt(words: List<String>): Int {
        return words.indexOf(this) + 1
    }
    fun getNumber(line: String, wordList: List<String>): Int {
        val (wordIdx, word) = line.findAnyOf(wordList) ?: (Int.MAX_VALUE to "not found")
        val (digitsIdx, digit) = line.findAnyOf(digits) ?: (Int.MAX_VALUE to "not found")
        
        return if (wordIdx < digitsIdx) {
            word.digitWordToInt(wordList)
        } else {
            digit.toInt()
        }
    }
    
    // actual code now
    _reader = inputFile("input")
    val lines = _reader.readLines()
    println(lines.sumOf { line ->
        getNumber(line, words) * 10 + getNumber(line.reversed(), revWords)
    })
}

fun originalSolution() {
    _reader = inputFile("input")
    val lines = _reader.readLines()
    var total = 0
    for (i in 1..lines.size) {
        val input = readLn()
        total += (input.findFirst() + input.findLast()).toInt()
    }
    println(total)
}


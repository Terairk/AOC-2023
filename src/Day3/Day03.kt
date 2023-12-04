package Day3

import org.utils.*
import java.math.BigInteger

fun main() {
    part1("day3")
    part2("day3")
}

fun <T> List<T>.indexOfFirstOrNull(
    block: (T) -> Boolean,
    ): Int? = indexOfFirst(block).takeIf { it != -1 }

fun checkIndices(range: IntRange, lineIndex: Int, lines: List<String>, number: Int): Int {
    var minCheck = range.start - 1
    val maxCheck = range.endInclusive + 1
    val listChecks = mutableListOf<Char?>()
    
    listChecks.add(lines[lineIndex].getOrNull(minCheck))
    listChecks.add(lines[lineIndex].getOrNull(maxCheck))
    
    if (minCheck < 0) minCheck = 0
    for (i in minCheck..maxCheck) {
        listChecks.add(lines.getOrNull(lineIndex - 1)?.getOrNull(i))
        listChecks.add(lines.getOrNull(lineIndex + 1)?.getOrNull(i))
    }
    
    
    val newList = listChecks.filterNotNull().map { it.toString()}.joinToString("")
    val regex = """[^\.\d]""".toRegex()
    val match = regex.find(newList)
    if (match != null) {
        return number
    } else {
        return 0
    }
}
data class Star(val lineIndex: Int, val range: Int)

fun checkIndices2(range: IntRange, lineIndex: Int, lines: List<String>, number: Int): Pair<Star, Int>? {
    fun checkStarRange(lineIndex: Int):Boolean? {
        return lines.getOrNull(lineIndex)?.substring(range)?.contains("*")
    }
    
    var minCheck = range.start - 1
    var maxCheck = range.endInclusive + 1   
    var starIndex = -1
    val maxSize = lines[lineIndex].length - 1
    if (maxCheck > maxSize) maxCheck = maxSize
    if (minCheck < 0) minCheck = 0
    val nRange = minCheck..maxCheck
    if (checkStarRange(lineIndex - 1) == true) {
        starIndex = lineIndex - 1
    } else if (checkStarRange(lineIndex + 1) == true) {
        starIndex = lineIndex + 1
    } else if (checkStarRange(lineIndex) == true) {
        starIndex = lineIndex
    }
    if (starIndex != -1) {
        val starPos = lines[starIndex].indexOf("*", minCheck)
        return Star(starIndex, starPos) to number
    } else {
        return null
    }
    
}


fun part1(filename: String) {
    _reader = inputFile(filename)
    val lines = _reader.readLines()
    var total = 0
    for ((index, line) in lines.withIndex()) {
        val regex = """\d+""".toRegex()
        val matchResults = regex.findAll(line)
        val numbers = matchResults.map { it.value.toInt() to it.range }.toList()
        for ((number, range) in numbers) {
            total += checkIndices(range, index, lines, number)
        }
    }
    
    println(total)
}

fun part2(filename: String) {
    _reader = inputFile(filename)
    val lines = _reader.readLines()
    var total = BigInteger.ZERO
    val starList = mutableListOf<Pair<Star, Int>>()
    for ((index, line) in lines.withIndex()) {
        val regex = """\d+""".toRegex()
        val matchResults = regex.findAll(line)
        val numbers = matchResults.map { it.value.toInt() to it.range }.toList()
        for ((number, range) in numbers) {
            val x = checkIndices2(range, index, lines, number)
            if (x != null) {
                starList.add(x)
            }
        }
    }
    
    val y = starList.groupBy (
        keySelector = { it.first },
        valueTransform = { it.second})
    
    for ((star, numbers) in y) {
        if (numbers.size == 2) {
            total += numbers[0] * numbers[1]
        }
    }
    
    println(total)
    
}
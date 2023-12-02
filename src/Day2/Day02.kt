package Day02

import org.utils.*
import java.math.BigInteger
import kotlin.math.max

fun main() {
    part2("day2")
}

fun part1(fileName: String) {
    _reader = inputFile(fileName)
    val lines = _reader.readLines()
    val (red, green, blue) = Triple(12, 13, 14)
    var total = 0
    loop@ for (line in lines) {
        val gameNum = line.substringAfter("Game ").substringBefore(": ").toInt()
        val groups  = line.substringAfter(": ").split("; ")
        for (group in groups) {
            val ballPairs = group.split(", ")
            
            val p = ballPairs.groupBy(
                keySelector = {it.split(" ").last()},
                valueTransform = {it.split(" ").first()})
            
            val (redCount, greenCount, blueCount) = Triple(p["red"]?.sumOf { it.toInt() } ?: 0
                , p["green"]?.sumOf { it.toInt() } ?: 0
                , p["blue"]?.sumOf {it.toInt() } ?: 0)
            if (!(redCount <= red && greenCount <= green && blueCount <= blue)) {
                continue@loop
            }
        }
        total += gameNum
    }
    
    println(total)
}

fun part2(fileName: String) {
    _reader = inputFile(fileName)
    val lines = _reader.readLines()
    val (red, green, blue) = Triple(12, 13, 14)
    var total = BigInteger.ZERO
    loop@ for (line in lines) {
        var (redMax, greenMax, blueMax) = Triple(0, 0, 0)
        val gameNum = line.substringAfter("Game ").substringBefore(": ").toInt()
        val groups  = line.substringAfter(": ").split("; ")
        for (group in groups) {
            val ballPairs = group.split(", ")

            val p = ballPairs.groupBy(
                keySelector = {it.split(" ").last()},
                valueTransform = {it.split(" ").first()})

            val (redCount, greenCount, blueCount) = Triple(p["red"]?.sumOf { it.toInt() } ?: 0
                , p["green"]?.sumOf { it.toInt() } ?: 0
                , p["blue"]?.sumOf {it.toInt() } ?: 0)
            redMax = max(redMax, redCount)
            greenMax = max(greenMax, greenCount)
            blueMax = max(blueMax, blueCount)
        }
        val powerSet = BigInteger("1") * redMax * greenMax * blueMax
        total += powerSet
    }

    println(total)
}


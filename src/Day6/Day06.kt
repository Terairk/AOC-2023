package Day6

import org.utils.*
import java.math.BigInteger
import kotlin.math.sqrt

fun calcLowerBound(maxTime: Long, distance: Long): Long{
    var mid: Long = 0
    var lo: Long = 0
    var hi = maxTime
    var result: Long = 0
    
    while (lo < hi) {
        mid = lo + (hi - lo) / 2
        
        if (calcDistance(maxTime, mid) > distance) {
            result = mid
            hi = mid - 1
        }
        if (calcDistance(maxTime, mid) < distance) {
            lo = mid + 1
        }
    }
    
    if (calcDistance(maxTime, result - 1) > distance) {
        return result - 1
    }
    return result
}

fun calcUpperBound(maxTime: Long, distance: Long): Long {
    var mid: Long = 0
    var lo: Long = 0
    var hi = maxTime
    var result: Long = 0

    while (lo < hi) {
        mid = lo + (hi - lo) / 2

        if (calcDistance(maxTime, mid) > distance) {
            result = mid
            lo = mid + 1
        }
        if (calcDistance(maxTime, mid) < distance) {
            hi = mid - 1
        }
    }

    if (calcDistance(maxTime, result + 1) > distance) {
        return result + 1
    }
    return result
}

fun calcDistance(maxTime: Long, timeDown: Long): Long {
    val speed = timeDown
    return (maxTime - timeDown) * speed
}

fun quadraticSol(maxTime: Long, maxDistance: Long): Long {
    val lowerBound = maxTime - sqrt((maxTime * maxTime).toDouble() - 4 * maxDistance.toDouble()) / 2
    val upperBound = maxTime + sqrt((maxTime * maxTime).toDouble() - 4 * maxDistance.toDouble()) / 2
    return (upperBound - lowerBound + 1).toLong()
}

fun part1(fileName: String) {
    _reader = inputFile(fileName)
    val lines = _reader.readLines()
    
    val times = lines[0].split(" ").map { it.toLong() }
    val maxDistance = lines[1].split(" ").map { it.toLong() }
    
    var total = BigInteger.ONE
    for (index in 0..<times.size) {
        val lowerBound = calcLowerBound(times[index], maxDistance[index])
        val upperBound = calcUpperBound(times[index], maxDistance[index])
        total *= (upperBound - lowerBound + 1)
    }
    
    println(total)
    
}

fun part2(fileName: String) {
    _reader = inputFile(fileName)
    val lines = _reader.readLines()

    val times = lines[0].split(" ").map { it.toLong() }
    val maxDistance = lines[1].split(" ").map { it.toLong() }

    var total = BigInteger.ONE
    var t2 = BigInteger.ONE
    for ((index, distance) in maxDistance.withIndex()) {
        val lowerBound = calcLowerBound(times[index], maxDistance[index])
        val upperBound = calcUpperBound(times[index], maxDistance[index])
        total *= (upperBound - lowerBound + 1)
        t2 *= quadraticSol(times[index], maxDistance[index])
        
    }
    

    println(total)
    println(t2)
}

fun main () {
    part1("Day6_test")
    part1("Day6")
    part2("Day6_test")
    part2("Day6")
    
}
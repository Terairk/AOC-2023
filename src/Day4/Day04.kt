package Day4

import org.utils.*
import java.math.BigInteger

fun part1(fileName: String) {
    _reader = inputFile(fileName)
    val lines = _reader.readLines()
    var total = 0
    
    loop@ for (line in lines) {
        val realLine = line.substringAfter(": ").split(" | ")
        val myNums = realLine[0].split(" ")
            .filter { it.isNotEmpty() }.map { it.toInt() }
        val totalNums = realLine[1].split(" ").filter { it.isNotEmpty() }.
        map { it.toInt() }
        
        var startValue = 1
        var count = 0
        for ((index, num) in myNums.withIndex()) {
            if (totalNums.contains(num)) {
                startValue *= 2
                count++
            }
        }
        if (count == 0) {
            startValue = 0
        } else
            startValue /= 2
        
        total += startValue
    }
    
    println(total)
}

fun part2(fileName: String) {
    _reader = inputFile(fileName)
    val lines = _reader.readLines()
    var total = 0
    val integerMap = HashMap<Int, MutableList<Int>>()

    val totalList = mutableListOf<Int>()
    for (index in 0..<lines.size) {
        integerMap[index] = mutableListOf()
        totalList.add(1)
    }

    var gameNumber = 0
    loop@ for (line in lines) {
        val realLine = line.substringAfter(": ").split(" | ")
        val myNums = realLine[0].split(" ")
            .filter { it.isNotEmpty() }.map { it.toInt() }
        val totalNums = realLine[1].split(" ").filter { it.isNotEmpty() }.map { it.toInt() }
        
        var count = 0
        for ((index, num) in myNums.withIndex()) {
            if (totalNums.contains(num)) {
                count++
                integerMap[count + gameNumber]?.add(gameNumber)
            }
        }
        for (num in integerMap[gameNumber]!!) {
            totalList[gameNumber] += totalList[num]
        }
        
        val x = 0
        gameNumber++
    }
    
    println(totalList.sum())
}


fun main() {
//    part1("day4")
//    part1("day4_test")
    part2("day4")
//    part2("day4_test")
}
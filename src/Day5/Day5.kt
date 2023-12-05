package Day5

import org.utils.*
import java.math.BigInteger

data class Mapping(val sourceStart : Long, val sourceRange : Long, 
                   val targetStart : Long, val targetRange: Long)

data class SeedRanges(val start: Long, val length: Long)

fun convertSeed(seedNumber: Long, mappings: List<Mapping>): Long {
    for (mapping in mappings) {
        if (seedNumber in mapping.sourceStart..mapping.sourceRange) {
            val offset = seedNumber - mapping.sourceStart
            return mapping.targetStart + offset
        }
    }
    
    return seedNumber
}

fun reverseSeed(seedNumber: Long, mappings: List<Mapping>): Long {
    for (mapping in mappings) {
        if (seedNumber in mapping.targetStart..mapping.targetRange) {
            val offset = seedNumber - mapping.targetStart
            return mapping.sourceStart + offset
        }
    }
    
    return seedNumber
}

fun part1(fileName: String) {
    _reader = inputFile(fileName)
    val lines = _reader.readLines()
    var total = 0
    
    val seeds = lines[0].substringAfter(": ").split(" ").map { it.toLong() }
    val categories = Array<List<Mapping>>(size = 7) { mutableListOf() }
    val mappingList = mutableListOf<Mapping>()
    var count = 0
    
    for ((index, line) in lines.withIndex()) {
        if (index == 0 || index == 1 || index == 2 || line.isBlank()) continue
        if ("map" in line) { 
            categories[count] = mappingList.toList()
            count++
            mappingList.clear()
            continue
        }
        
        val nums = line.split(" ").map { it.toLong() }
        val sourceRange = nums[1]..<(nums[1] + nums[2])
        val targetRange = nums[0]..<(nums[0] + nums[2])
        mappingList.add(Mapping(nums[1], nums[1] + nums[2] - 1, nums[0], nums[0] + nums[2] - 1))
        
    }
    categories[count] = mappingList
    
    val locations = mutableListOf<Long>()
    
    for (seed in seeds) {
        val a =convertSeed(seed, categories[0])
        val b = convertSeed(a, categories[1])
        val c = convertSeed(b, categories[2])
        val d = convertSeed(c, categories[3])
        val e = convertSeed(d, categories[4])
        val f = convertSeed(e, categories[5])
        val g = convertSeed(f, categories[6])
        locations.add(g)
    }
    
    println(locations.min())
    
}

fun part2(fileName: String) {
    _reader = inputFile(fileName)
    val lines = _reader.readLines()
    var total = 0
    val seedRanges = lines[0].substringAfter(": ").split(" ").map { it.toLong() }
    val categories = Array<List<Mapping>>(size = 7) { mutableListOf() }
    val mappingList = mutableListOf<Mapping>()
    var count = 0

    for ((index, line) in lines.withIndex()) {
        if (index == 0 || index == 1 || index == 2 || line.isBlank()) continue
        if ("map" in line) {
            categories[count] = mappingList.toList()
            count++
            mappingList.clear()
            continue
        }

        val nums = line.split(" ").map { it.toLong() }
        val sourceRange = nums[1]..<(nums[1] + nums[2])
        val targetRange = nums[0]..<(nums[0] + nums[2])
        mappingList.add(Mapping(nums[1], nums[1] + nums[2] - 1, nums[0], nums[0] + nums[2] - 1))

    }
    categories[count] = mappingList
    val seeds= mutableListOf<Long>()

//    for (i in 0..<seedRanges.size / 2) {
//        val start = seedRanges[2 * i]
//        val length = seedRanges[2 * i + 1]
//        var j = start
//        println("start: $start, length: $length")
//        while (j < start + length) {
//            seeds.add(j)
//            j++
//        }
//    }
    
    val seedRange = mutableListOf<SeedRanges>()
    for (i in 0..<seedRanges.size / 2) {
        val start = seedRanges[2 * i]
        val length = seedRanges[2 * i + 1]
        seedRange.add(SeedRanges(start, length))
    }

    var found = false
    var locations: Long = 0
    while (!found) {
        // reverse process
        val a = reverseSeed(locations, categories[6])
        val b = reverseSeed(a, categories[5])
        val c = reverseSeed(b, categories[4])
        val d = reverseSeed(c, categories[3])
        val e = reverseSeed(d, categories[2])
        val f = reverseSeed(e, categories[1])
        val g = reverseSeed(f, categories[0])
        for (range in seedRange) {
            if (g > range.start && g < range.start + range.length) {
                found = true
                println(locations)
                break
            }
        }

        locations++
    }
    
//    val locations = mutableListOf<Long>()
//    for (seed in seeds) {
//        val a =convertSeed(seed, categories[0])
//        val b = convertSeed(a, categories[1])
//        val c = convertSeed(b, categories[2])
//        val d = convertSeed(c, categories[3])
//        val e = convertSeed(d, categories[4])
//        val f = convertSeed(e, categories[5])
//        val g = convertSeed(f, categories[6])
//        locations.add(g)
//    }
//
//
//
//    println(locations.min())
}


fun main() {
    part1("day5")
//    part1("day5_test") 
    part2("day5")
//    part2("day5_test")
}


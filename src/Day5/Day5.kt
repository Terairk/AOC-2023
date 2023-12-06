//package Day5
//
//import org.utils.*
//import java.math.BigInteger
//
//data class sourceDest(val sourceStart : Long, val sourceRange : Long,
//                      val targetStart : Long, val targetRange: Long)
//
//data class stringSourceDest(val sourceStart : String, val sourceRange : String,
//                      val targetStart : String, val targetRange: String, val tLength: Int)
//data class Mapping(val SD : sourceDest, val SSD : stringSourceDest)
//
//data class SeedRanges(val start: Long, val end: Long)
//
//fun convertSeed(seedNumber: Long, mappings: List<Mapping>): Long {
//    for (mapping in mappings) {
//        if (seedNumber in mapping.SD.sourceStart..mapping.SD.sourceRange) {
//            val offset = seedNumber - mapping.SD.sourceStart
//            return mapping.SD.targetStart + offset
//        }
//    }
//    
//    return seedNumber
//}
//
//fun reverseSeed(seedNumber: Long, mappings: List<Mapping>): Long {
//    for (mapping in mappings) {
//        if (seedNumber >= mapping.SD.targetStart && seedNumber <= mapping.SD.targetRange) {
//            val offset = seedNumber - mapping.SD.targetStart
//            return mapping.SD.sourceStart + offset
//        }
//    }
//    
//    return seedNumber
//}
//
//fun sReverseSeed(seedNumber: Long, mappings: List<Mapping>): Long {
//    val sSeedNumber = seedNumber.toString()
//    val lSeed = sSeedNumber.length
//    for (mapping in mappings) {
//        if (sSeedNumber >= mapping.SSD.targetStart && sSeedNumber <= mapping.SSD.targetRange && lSeed >= mapping.SSD.tLength) {
//            val offset = seedNumber - mapping.SD.targetStart
//            return mapping.SD.sourceStart + offset
//        }
//    }
//
//    return seedNumber
//}
//
//
//fun part1(fileName: String) {
//    _reader = inputFile(fileName)
//    val lines = _reader.readLines()
//    var total = 0
//    
//    val seeds = lines[0].substringAfter(": ").split(" ").map { it.toLong() }
//    val categories = Array<List<Mapping>>(size = 7) { mutableListOf() }
//    val mappingList = mutableListOf<Mapping>()
//    var count = 0
//    
//    for ((index, line) in lines.withIndex()) {
//        if (index == 0 || index == 1 || index == 2 || line.isBlank()) continue
//        if ("map" in line) { 
//            categories[count] = mappingList.toList()
//            count++
//            mappingList.clear()
//            continue
//        }
//        
//        val nums = line.split(" ").map { it.toLong() }
//        val mp1 = sourceDest(nums[1], nums[1] + nums[2] - 1, nums[0], nums[0] + nums[2] - 1)
//        val mp2 = stringSourceDest(nums[1].toString(), (nums[1] + nums[2] - 1).toString(),
//            nums[0].toString(), (nums[0] + nums[2] - 1).toString(), nums[1].toString().length)
//        mappingList.add(Mapping(mp1, mp2))
//        
//    }
//    categories[count] = mappingList
//    
//    val locations = mutableListOf<Long>()
//    
//    for (seed in seeds) {
//        val a = convertSeed(seed, categories[0])
//        val b = convertSeed(a, categories[1])
//        val c = convertSeed(b, categories[2])
//        val d = convertSeed(c, categories[3])
//        val e = convertSeed(d, categories[4])
//        val f = convertSeed(e, categories[5])
//        val g = convertSeed(f, categories[6])
//        locations.add(g)
//    }
//    
//    println(locations.min())
//    
//}
//
//fun part2(fileName: String) {
//    _reader = inputFile(fileName)
//    val lines = _reader.readLines()
//    var total = 0
//    val seedRanges = lines[0].substringAfter(": ").split(" ").map { it.toLong() }
//    val categories = Array<List<Mapping>>(size = 7) { mutableListOf() }
//    val mappingList = mutableListOf<Mapping>()
//    var count = 0
//
//    for ((index, line) in lines.withIndex()) {
//        if (index == 0 || index == 1 || index == 2 || line.isBlank()) continue
//        if ("map" in line) {
//            categories[count] = mappingList.toList()
//            count++
//            mappingList.clear()
//            continue
//        }
//
//        val nums = line.split(" ").map { it.toLong() }
//        val mp1 = sourceDest(nums[1], nums[1] + nums[2] - 1, nums[0], nums[0] + nums[2] - 1)
//        val mp2 = stringSourceDest(nums[1].toString(), (nums[1] + nums[2] - 1).toString(),
//            nums[0].toString(), (nums[0] + nums[2] - 1).toString(), nums[1].toString().length)
//        mappingList.add(Mapping(mp1, mp2))
//    }
//    categories[count] = mappingList
//    val seeds= mutableListOf<Long>()
//
////    for (i in 0..<seedRanges.size / 2) {
////        val start = seedRanges[2 * i]
////        val length = seedRanges[2 * i + 1]
////        var j = start
////        println("start: $start, length: $length")
////        while (j < start + length) {
////            seeds.add(j)
////            j++
////        }
////    }
//    
//    val seedRange = mutableListOf<SeedRanges>()
//    for (i in 0..<seedRanges.size / 2) {
//        val start = seedRanges[2 * i]
//        val length = seedRanges[2 * i + 1]
//        seedRange.add(SeedRanges(start, start + length))
//    }
//
//    var found = false
//    var locations: Long = 0
//    while (!found) {
//        // reverse process
////        val a = reverseSeed(locations, categories[6])
////        val b = reverseSeed(a, categories[5])
////        val c = reverseSeed(b, categories[4])
////        val d = reverseSeed(c, categories[3])
////        val e = reverseSeed(d, categories[2])
////        val f = reverseSeed(e, categories[1])
////        val g = reverseSeed(f, categories[0])
//        val g = reverseSeed(reverseSeed(reverseSeed(reverseSeed(reverseSeed(reverseSeed(reverseSeed(
//            locations, categories[6]), categories[5]), categories[4]),
//            categories[3]), categories[2]), categories[1]), categories[0])
//        for (range in seedRange) {
//            if (g > range.start && g < range.end) {
//                found = true
//                println(locations)
//                break
//            }
//        }
//
//        locations++
//    }
//
////    val gx = reverseSeed(reverseSeed(reverseSeed(reverseSeed(reverseSeed(reverseSeed(reverseSeed(
////        8, categories[6]), categories[5]), categories[4]),
////        categories[3]), categories[2]), categories[1]), categories[0])
////    val gy = sReverseSeed(sReverseSeed(sReverseSeed(sReverseSeed(sReverseSeed(sReverseSeed(sReverseSeed(
////        8, categories[6]), categories[5]), categories[4]),
////        categories[3]), categories[2]), categories[1]), categories[0])
//
//    val tx = reverseSeed(8, categories[6])
//    val ty = sReverseSeed(8, categories[6])
//    println("GX: $tx, GY: $ty")
//    
////    val locations = mutableListOf<Long>()
////    for (seed in seeds) {
////        val a =convertSeed(seed, categories[0])
////        val b = convertSeed(a, categories[1])
////        val c = convertSeed(b, categories[2])
////        val d = convertSeed(c, categories[3])
////        val e = convertSeed(d, categories[4])
////        val f = convertSeed(e, categories[5])
////        val g = convertSeed(f, categories[6])
////        locations.add(g)
////    }
////
////
////
////    println(locations.min())
//}
//
//
//fun main() {
//    part1("day5")
////    part1("day5_test") 
//    part2("day5")
////    part2("day5_test")
//}
//
//suspend fun over

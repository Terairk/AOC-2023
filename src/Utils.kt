package org.utils

import java.io.File
import java.io.PrintWriter
import java.math.BigInteger
import java.security.MessageDigest
import java.util.*
import kotlin.io.path.Path
import kotlin.io.path.readLines
import kotlin.math.*
/**
 * Reads lines from the given input txt file.
 */
fun readInput(name: String) = Path("src/$name.txt").readLines()
fun <T> List<T>.indexOfFirstOrNull(
    block: (T) -> Boolean,
): Int? = indexOfFirst(block).takeIf { it != -1 }

private inline fun <T, R> Iterable<T>.lastNotNullOf(block: (T) -> R?): R {
    return reversed().firstNotNullOf(block)
}

private inline fun <T, R> List<T>.lastNotNullOf(block: (T) -> R?): R {
    return asReversed().firstNotNullOf(block)
}

/**
 * Converts string to md5 hash.
 */
fun String.md5() = BigInteger(1, MessageDigest.getInstance("MD5").digest(toByteArray()))
    .toString(16)
    .padStart(32, '0')

/**
 * The cleaner shorthand for printing output.
 */
fun Any?.println() = println(this)

fun inputFile(name: String) = File("src/TestFiles/$name.txt").bufferedReader()

@JvmField val INPUT = inputFile("input")

// @JvmField val INPUT = System.`in`
@JvmField val OUTPUT = System.out

@JvmField var _reader = INPUT
fun readLine(): String? = _reader.readLine()
fun readLn() = _reader.readLine()!!

@JvmField var _tokenizer: StringTokenizer = StringTokenizer("")
fun read(): String {
    while (_tokenizer.hasMoreTokens().not()) _tokenizer = StringTokenizer(_reader.readLine() ?: return "", " ")
    return _tokenizer.nextToken()
}
fun readInt() = read().toInt()
fun readDouble() = read().toDouble()
fun readLong() = read().toLong()
fun readStrings(n: Int) = List(n) { read() }
fun readLines(n: Int) = List(n) { readLn() }
fun readInts(n: Int) = List(n) { read().toInt() }
fun readIntArray(n: Int) = IntArray(n) { read().toInt() }
fun readDoubles(n: Int) = List(n) { read().toDouble() }
fun readDoubleArray(n: Int) = DoubleArray(n) { read().toDouble() }
fun readLongs(n: Int) = List(n) { read().toLong() }
fun readLongArray(n: Int) = LongArray(n) { read().toLong() }

@JvmField val _writer = PrintWriter(OUTPUT, false)
inline fun output(block: PrintWriter.() -> Unit) { _writer.apply(block).flush() }

operator fun String.times(n: Int): String {
    val sb = StringBuilder()
    repeat(n) {
        sb.append(this)
    }
    return sb.toString()
}

// Big integer Utils
operator fun BigInteger.times(intValue: Int): BigInteger {
    return this.multiply(BigInteger.valueOf(intValue.toLong()))
}

operator fun BigInteger.div(intValue: Int): BigInteger {
    return this.divide(BigInteger.valueOf(intValue.toLong()))
}

operator fun BigInteger.plus(intValue: Int): BigInteger {
    return this.add(BigInteger.valueOf(intValue.toLong()))
}

operator fun BigInteger.minus(intValue: Int): BigInteger {
    return this.subtract(BigInteger.valueOf(intValue.toLong()))
}

operator fun BigInteger.times(intValue: Long): BigInteger {
    return this.multiply(BigInteger.valueOf(intValue.toLong()))
}

operator fun BigInteger.div(intValue: Long): BigInteger {
    return this.divide(BigInteger.valueOf(intValue.toLong()))
}

operator fun BigInteger.plus(intValue: Long): BigInteger {
    return this.add(BigInteger.valueOf(intValue.toLong()))
}

operator fun BigInteger.minus(intValue: Long): BigInteger {
    return this.subtract(BigInteger.valueOf(intValue.toLong()))
}

operator fun BigInteger.rangeTo(other: BigInteger) =
    BigIntegerRange(this, other)

class BigIntegerRange(
    override val start: BigInteger,
    override val endInclusive: BigInteger
                     ) : ClosedRange<BigInteger>, Iterable<BigInteger> {
    override operator fun iterator(): Iterator<BigInteger> =
        BigIntegerRangeIterator(this)
}

class BigIntegerRangeIterator(
    private val range: ClosedRange<BigInteger>
                             ) : Iterator<BigInteger> {
    private var current = range.start

    override fun hasNext(): Boolean =
        current <= range.endInclusive

    override fun next(): BigInteger {
        if (!hasNext()) {
            throw NoSuchElementException()
        }
        return current++
    }
}
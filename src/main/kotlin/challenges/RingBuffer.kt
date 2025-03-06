package challenges

fun main() {
    val ringBuffer = RingBuffer<Int>(5)

    ringBuffer.push(1)
    println(ringBuffer)
    ringBuffer.push(2)
    println(ringBuffer)

    ringBuffer.pop()
    println(ringBuffer)

    ringBuffer.push(3)
    println(ringBuffer)
    ringBuffer.push(4)
    println(ringBuffer)

    ringBuffer.pop()
    println(ringBuffer)

    ringBuffer.push(5)
    println(ringBuffer)
    ringBuffer.push(6)
    println(ringBuffer)
    ringBuffer.push(7)
    println(ringBuffer)

    ringBuffer.pop()
    println(ringBuffer)
    ringBuffer.pop()
    println(ringBuffer)

    ringBuffer.push(8)
    println(ringBuffer)
    ringBuffer.push(9)
    println(ringBuffer)
    ringBuffer.push(10)
    println(ringBuffer)
}

class RingBuffer<T>(capacity: Int) {
    private val buffer: Array<Any?> = arrayOfNulls<Any?>(capacity)

    private var head: Int = -1
    private var tail: Int = 0

    fun push(t: T) {
        head++

        val position = head % buffer.size

        buffer[position] = t
    }

    @Suppress("UNCHECKED_CAST")
    fun pop(): T {
        val position = tail % buffer.size

        tail++

        val value = buffer[position] as T

        buffer[position] = null

        return value
    }

    override fun toString(): String {
        return buffer.contentToString()
    }
}

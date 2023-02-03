package challenges

fun main() {
    val priorityQueue = PriorityQueue(4)

    priorityQueue.queue(PriorityItem("Nolasco", 2))
    priorityQueue.queue(PriorityItem("Antonio", 3))
    priorityQueue.queue(PriorityItem("Alvarado", 1))
    priorityQueue.queue(PriorityItem("Daniel", 4))

    println(priorityQueue.peek())
    println(priorityQueue.dequeue())
    println(priorityQueue.peek())
    println(priorityQueue.dequeue())
    println(priorityQueue.peek())
    println(priorityQueue.dequeue())
    println(priorityQueue.peek())
    println(priorityQueue.dequeue())
}

data class PriorityItem(val value: String, val priority: Int)

class PriorityQueue(private val size: Int) {
    private val priorityItems = Array<PriorityItem?>(size, init = { null })
    private var internalSize = 0

    fun queue(priorityItem: PriorityItem) {
        if (internalSize < size) {
            var insertIndex = -1

            for (i in 0 until priorityItems.size) {
                if (priorityItems[i] == null) {
                    insertIndex = i
                    break
                }
            }

            priorityItems[insertIndex] = priorityItem
            internalSize += 1
        } else {
            throw  Exception("Queue is full")
        }
    }

    fun dequeue(): PriorityItem {
        val index = getMostImportantIndex()
        val item = priorityItems[index]

        priorityItems[index] = null

        internalSize -= 1

        return item!!
    }

    fun peek(): PriorityItem {
        val index = getMostImportantIndex()

        return priorityItems[index]!!
    }

    private fun getMostImportantIndex(): Int {
        var index = -1
        var maxPriority: Int? = null

        for (i in 0 until priorityItems.size) {
            val priorityItem: PriorityItem? = priorityItems[i];

            if (priorityItem != null && (maxPriority == null || priorityItem.priority > maxPriority)) {
                maxPriority = priorityItem.priority
                index = i
            }
        }

        if (index < 0) {
            throw  Exception("Queue is empty")
        }

        return index
    }

    override fun toString(): String {
        return "$priorityItems"
    }
}
package challenges

fun main() {
    val queue = LinkedQueue()

    println(queue)

    println("PUSH")
    queue.push("A")
    queue.push("B")
    queue.push("C")
    queue.push("D")
    queue.push("E")
    queue.push("F")
    queue.push("G")
    queue.push("H")
    queue.push("I")
    queue.push("J")
    println(queue)

    println("POP")
    println("queue.pop(): ${queue.pop()}")
    println("queue.pop(): ${queue.pop()}")
    println("queue.pop(): ${queue.pop()}")
    println("queue.pop(): ${queue.pop()}")
    println(queue)

    println("PEEK")
    println("queue.peek(): ${queue.peek()}")
    println("queue.peek(): ${queue.peek()}")
    println(queue)
}

class LinkedQueue {
    var head: SinglyNode? = null

    fun push(item: String) {
        if (head == null) {
            head = SinglyNode(item)
        } else {
            var current = head

            while (current?.next != null) {
                current = current.next
            }

            current?.next = SinglyNode(item)
        }
    }

    fun pop(): String? {
        val item = head?.value

        head = head?.next

        return item
    }

    fun peek(): String? {
        return head?.value
    }

    override fun toString(): String {
        var string = "{"
        var current = head

        while (current != null) {
            string += " ${current.value} ⬅"

            current = current.next
        }

        string += " }"

        return string
    }
}
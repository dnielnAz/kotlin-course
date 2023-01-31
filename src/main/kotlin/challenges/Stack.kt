package challenges

fun main() {
    val stack = ArrayStack(10)

    println(stack)

    println("PUSH")
    stack.push("A")
    stack.push("B")
    stack.push("C")
    stack.push("D")
    stack.push("E")
    stack.push("F")
    stack.push("G")
    stack.push("H")
    stack.push("I")
    stack.push("J")
    println(stack)

    println("POP")
    println("stack.pop(): ${stack.pop()}")
    println("stack.pop(): ${stack.pop()}")
    println("stack.pop(): ${stack.pop()}")
    println("stack.pop(): ${stack.pop()}")
    println(stack)

    println("PEEK")
    println("stack.peek(): ${stack.peek()}")
    println("stack.peek(): ${stack.peek()}")
    println(stack)

    println("---------------------------------------------------------------------------------------------------------")

    val linkedStack = LinkedStack()

    println(linkedStack)

    println("PUSH")
    linkedStack.push("A")
    linkedStack.push("B")
    linkedStack.push("C")
    linkedStack.push("D")
    linkedStack.push("E")
    linkedStack.push("F")
    linkedStack.push("G")
    linkedStack.push("H")
    linkedStack.push("I")
    linkedStack.push("J")
    println(linkedStack)

    println("POP")
    println("stack.pop(): ${linkedStack.pop()}")
    println("stack.pop(): ${linkedStack.pop()}")
    println("stack.pop(): ${linkedStack.pop()}")
    println("stack.pop(): ${linkedStack.pop()}")
    println(linkedStack)

    println("PEEK")
    println("stack.peek(): ${linkedStack.peek()}")
    println("stack.peek(): ${linkedStack.peek()}")
    println(linkedStack)
}

class ArrayStack(size: Int) {
    private val items = Array<String?>(size) { null }
    private var index = -1

    fun push(item: String): Boolean {
        return if (index < items.size - 1) {
            index++

            items[index] = item

            true
        } else {
            false
        }
    }

    fun peek(): String? {
        return items[index]
    }

    fun pop(): String? {
        return if (index >= 0) {
            val item = items[index]
            index--

            item
        } else {
            null
        }
    }

    override fun toString(): String {
        var string = "{"

        for (i in 0..index) {
            string += " ${items[i]} ⬅"
        }

        return "$string }"
    }
}

class LinkedStack {
    private var head: SinglyNode? = null

    fun push(item: String): Boolean {
        if (head == null) {
            head = SinglyNode(item)
        } else {
            var current = head

            while (current?.next != null) {
                current = current.next
            }

            current?.next = SinglyNode(item)
        }

        return true
    }

    fun pop(): String? {
        var current = head
        var previous: SinglyNode? = null

        while (current?.next != null) {
            previous = current
            current = current.next
        }

        val result = current?.value

        previous?.next = null

        return result
    }

    fun peek(): String? {
        var current = head

        while (current?.next != null) {
            current = current.next
        }

        return current?.value
    }

    override fun toString(): String {
        var string = "{"
        var current = head

        while (current != null) {
            string += " ${current.value} ⬅"

            current = current.next
        }

        return "$string }"
    }
}
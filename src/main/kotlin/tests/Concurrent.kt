package tests

import kotlinx.coroutines.*

fun main() {
    runBlocking {
        ConcurrentTest(this).start()
    }
}

class ConcurrentTest(private val scope: CoroutineScope) {

    private val withLet = true
    private var currentValue: Int? = null
    private var timer: Job? = null

    fun start() {
        currentValue = 0

        timer = scope.launch {
            while (currentValue != 10) {
                println("-----------------------------------------------------")
                if (withLet) {
                    currentValue?.let {
                        println("Working with input: $it")
                        delay(1000)
                        println("Must be the same as input: $it")
                    }
                } else {
                    if (currentValue != null) {
                        println("Working with input: $currentValue")
                        delay(1000)
                        println("Must be the same as input: $currentValue")
                    }
                }
                println("-----------------------------------------------------")
            }
        }

        scope.launch {
            while (currentValue != 10) {
                currentValue = (currentValue ?: 0) + 1

                delay(500)
            }
        }
    }
}
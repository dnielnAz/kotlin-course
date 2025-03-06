package other

import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.sync.Mutex
import java.time.LocalTime

fun main() {
    runBlocking {
        val calls = TooManyCalls()

        launch {
            for (i in 1..10) {
                val time = calls.getTime()

                println("launch a loop: $i | time: $time")

                delay(1000)
            }
        }

        launch {
            for (i in 1..10) {
                val time = calls.getTime()

                println("launch b loop: $i | time: $time")
            }
        }

            for(i in 1..10) {
                val time = calls.getTime()

                println("loop: $i | time: $time")
            }
    }
}

class TooManyCalls {
    private val mutex = Mutex()

    private var getTimeLastCalledAt = 0L


    suspend fun getTime(): String {
        val millis = System.currentTimeMillis()
        var date = "LOCKED"

        if (millis - getTimeLastCalledAt > 3000) {
            getTimeLastCalledAt = millis
            date = LocalTime.now().toString()
        }
        return date
    }
}
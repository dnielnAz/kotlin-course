package libs.arrow

import arrow.core.Either
import arrow.core.left
import arrow.core.right
import kotlinx.coroutines.delay
import kotlinx.coroutines.runBlocking

fun main() {
    runBlocking {
        getData()
    }
}

suspend fun getData(): Either<Throwable, String> {
    if (shouldFetchRemoteData()) {
        getRemoteData().fold(
            {
                return it.left()
            },
            {
                saveData(it)
            },
        )
    }

    return getLocalData().right()
}

suspend fun shouldFetchRemoteData(): Boolean {
    println("shouldFetchRemoteData: Started")
    delay(1000)
    println("shouldFetchRemoteData: Finished")
    return true
}

suspend fun getRemoteData(): Either<Throwable, String> {
    println("getRemoteData: Started")
    delay(1000)
    println("getRemoteData: Finished")
    return Either.catch { throw Exception("Not found") }
}

suspend fun saveData(string: String) {
    println("saveData: Started")
    delay(1000)
    println("---$string---")
    println("saveData: Finished")
}

suspend fun getLocalData(): String {
    println("getLocalData: Started")
    delay(1000)
    println("getLocalData: Finished")
    return ""
}
package libs.joda

import org.joda.time.DateTime
import org.joda.time.DateTimeZone

fun main() {
    val now = DateTime.now(DateTimeZone.UTC)
    val nowMinus7Days = now.minusDays(7)
    val nowMinus7DaysInHours = now.minusHours(24 * 7)

    println("now: $now")
    println("nowMinus7Days: $nowMinus7Days")
    println("nowMinus7DaysInHours: $nowMinus7DaysInHours")
}
package lessons

import java.text.SimpleDateFormat
import java.time.Instant
import java.time.OffsetDateTime
import java.time.ZoneOffset
import java.util.*


open class WaterSupply(var needsProcessing: Boolean)

class TapWater : WaterSupply(true) {
    fun addChemicalCleaners() {
        needsProcessing = false
    }
}

class FishStoreWater : WaterSupply(false)

class LakeWater : WaterSupply(true) {
    fun filter() {
        needsProcessing = false
    }
}

/*
    val and var are about the VALUES of variables.
    in and out are about the TYPES of variables.
    They make sure that when working with generic types, only safe types are passed in and out of functions.
 */
class Aquarium<out T : WaterSupply>(val waterSupply: T) {
    fun addWater(cleaner: Cleaner<T>) {
        if (waterSupply.needsProcessing) {
            cleaner.clean(waterSupply)
        }
        println("water added")
    }

    /*
        Once a type is reified, you can use it like a normal type—because it is a real type after inlining.
        That means you can do is checks using the type.

        We can access the class information of the generic at compile time
     */
    inline fun <reified R: WaterSupply> hasWaterSupplyOfType() = waterSupply is R
}

interface Cleaner<in T: WaterSupply> {
    fun clean(waterSupply: T)
}

class TapWaterCleaner : Cleaner<TapWater> {
    override fun clean(waterSupply: TapWater) =   waterSupply.addChemicalCleaners()
}

fun <T: WaterSupply> isWaterClean(aquarium: Aquarium<T>) {
    println("aquarium water is clean: ${!aquarium.waterSupply.needsProcessing}")
}
// reified: The generic type parameter has been made into a real type
inline fun <reified T: WaterSupply> WaterSupply.isOfType() = this is T
inline fun <reified R: WaterSupply> Aquarium<*>.hasWaterSupplyOfType2() = waterSupply is R

fun genericsExample() {
    val aquarium = Aquarium(TapWater())
    println(aquarium.hasWaterSupplyOfType<TapWater>())
    println(aquarium.waterSupply.isOfType<TapWater>())
    println(aquarium.hasWaterSupplyOfType2<TapWater>())
}

fun main() {
    val d = Date()
    val instant = Instant.now()

    println(d.time)

    val formatter = SimpleDateFormat("yyyy-MM-dd HH:mm:ss", Locale.getDefault())

    val v = formatter.format(d.time)

    println(v)

    val odt = OffsetDateTime.ofInstant(instant, ZoneOffset.systemDefault())
    println(instant.toEpochMilli())
    println(odt.toString())
}
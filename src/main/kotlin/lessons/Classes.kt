package lessons

import java.lang.Math.PI

/*
    public - visible outside the class.
    internal - visible within that module.
    private - visible in that class or source file.
    protected - visible to any subclasses.

    Using == is structural equality. Check whether two variables refer to the same object (referential equality) use ===.
 */

open class AquariumTank (open var length: Int = 100, open var width: Int = 20, open var height: Int = 40) {
    open var volume: Int
        get() = width * height * length / 1000
        set(value) {
            height = (value * 1000) / (width * length)
        }

    open val shape = "rectangle"

    open var water: Double = 0.0
        get() = volume * 0.9
}

class TowerTank (override var height: Int, var diameter: Int): AquariumTank(height = height, width = diameter, length = diameter) {
    override var volume: Int
        // ellipse area = π * r1 * r2
        get() = (width/2 * length/2 * height / 1000 * PI).toInt()
        set(value) {
            height = ((value * 1000 / PI) / (width/2 * length/2)).toInt()
        }

    override var water = volume * 0.8
    override val shape = "cylinder"
}

// Inheritance with abstract class
abstract class AquariumFish {
    abstract val color: String
}

// Composition using interfaces
interface FishAction  {
    fun eat()
}

class Shark: AquariumFish(), FishAction {
    override val color = "gray"
    override fun eat() {
        println("hunt and eat fish")
    }
}

class Plecostomus: AquariumFish(), FishAction {
    override val color = "gold"
    override fun eat() {
        println("eat algae")
    }
}

data class Decoration(val rocks: String, val wood: String, val diver: String){
}

fun makeDecorations() {
    val d5 = Decoration("crystal", "wood", "diver")
    println(d5)

    // Assign all properties to variables. use _ to skip assignment
    val (rock, _, diver) = d5
    println(rock)
    println(diver)
}

enum class Direction(val degrees: Int) {
    NORTH(0), SOUTH(180), EAST(90), WEST(270)
}
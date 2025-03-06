package courses.basics

/**
 * The methods of an interface are implemented by a helper (or delegate) object, which is then used by a class.
 */
object InterfaceDelegation {
    interface FishAction {
        fun eat()
    }

    interface FishColor {
        val color: String
    }

    object GoldColor : FishColor {
        override val color = "gold"
    }

    class PrintingFishAction(val food: String) : FishAction {
        override fun eat() {
            println(food)
        }
    }

    /**
     * Every time color is accessed, it is delegated to GoldColor.
     * Every time eat is invoked, it is delegated to PrintingFishAction.
     */
    class Plecostomus(fishColor: FishColor = GoldColor) : FishAction by PrintingFishAction("eat algae"),
        FishColor by fishColor

    class Shark : FishAction, FishColor {
        override val color = "gray"
        override fun eat() {
            println("hunt and eat fish")
        }
    }
}
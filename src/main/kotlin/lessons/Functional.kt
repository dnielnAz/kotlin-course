package lessons

/*
    run() returns the result of applying the function
    apply() returns the object after applying the function.
    let() returns a copy of the object with the changes.
 */

/*
    myWith(fish.name) {
        capitalize()
    }

    |
    |       Lambdas are objects...
   \ /

    myWith(fish.name, object : Function1<String, Unit> {
        override fun invoke(name: String) {
            name.capitalize()
        }
    })

    Marking a function as inline means that the compiler will change the code
    to replace the lambda with the instructions inside the lambda.

    inline myWith(fish.name) {
        capitalize()
    }

    |
    |       More work to the compiler but results in...
   \ /

    fish.name.capitalize()
 */

@Target(AnnotationTarget.PROPERTY_GETTER)
annotation class OnGet
@Target(AnnotationTarget.PROPERTY_SETTER)
annotation class OnSet

@ImAPlant
class Plant {
    @get:OnGet
    val isGrowing: Boolean = true

    @set:OnSet
    var needsFood: Boolean = false
}


fun testAnnotations() {
    val plantObject = Plant::class
    for (a in plantObject.annotations) {
        println(a.annotationClass.simpleName)
    }
}

annotation class ImAPlant

fun labels() {
    outerLoop@ for (i in 1..100) {
        print("$i ")
        for (j in 1..100) {
            if (i > 10) break@outerLoop  // breaks to outer loop
        }
    }
}

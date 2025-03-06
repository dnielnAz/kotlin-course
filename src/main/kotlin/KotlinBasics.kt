import kotlinx.coroutines.*
import java.time.Instant
import java.time.LocalDate
import java.time.ZoneId
import java.time.ZonedDateTime
import java.time.format.DateTimeFormatter
import kotlin.reflect.KProperty

fun kotlinBasics() {
    dsl()
}

// Data types
fun dataTypes() {
    val int = 0 // 32 bits
    val long = 0L // 64 bits
    val float = 0.0F // 32 bits
    val double = 0.0 // 64 bits
    val string = "asdasd"
}

// Concat strings
fun concatStrings() {
    val string = "daniel"
    val string2 = "antonio"

    val string3 = "first name: $string second name size: ${string2.length}"

    println(string3)
}

// dates
fun dates() {
    val instant: Instant = Instant.now() // UTC
    val zonedDateTime: ZonedDateTime = instant.atZone(ZoneId.systemDefault())
    val localDate: LocalDate = LocalDate.now()

    val dateTimeFormatter = DateTimeFormatter.ISO_OFFSET_DATE_TIME

    println("instant: $instant")
    println("zonedDateTime: $zonedDateTime")
    println("localDate: $localDate")
    println("zonedDateTime formatted: ${dateTimeFormatter.format(zonedDateTime)}")
}

// Val, Var and Const
fun valVarAndConst() {
    val myName = "this cannot be changed, was calculated at runtime"
    var myLastName = "this can be changed"
}

const val MY_NAME = "this cannot be changed, was calculated at compile time"

// Functions (Returns, Unit, Parameters, Named parameters, Lambdas, Default values, IT)
fun functions() {
    val result = myFunction(number2 = 3, number = 2)

    saySomething("Daniel", 2) { string ->
        println(string)

        return@saySomething 1
    }

    saySomething(string = "Daniel", number = 2) { // scope
        println(it)
        1
    }

    saySomething(
        string = "Daniel",
        number = 2,
        print = {
            println(it)
            0
        },
    )
}

fun myFunction(number: Int, number2: Int): Int {
    return number * number
}

// Lambdas must be the last parameter
fun saySomething(string: String, number: Int = 0, print: (String) -> Int): Unit {
    print(string)
}

// loops Conditional assignment and Conditional return
fun loopsAndConditionals() {
    for (i in 0..9) {
        println("For: $i")
    }

    val list = listOf(
        1,
        3,
        4,
        7,
    )

    list.forEach { println("forEach: $it") }

    var index = 0

    while (index < list.size) {
        println("while: ${list[index]}")
        index++
    }

    // Conditionals

    val isDark = false

    if (isDark) {
        println("This is dark")
    } else {
        println("This is NOT dark")
    }

    val number = if (isDark) {
        0
    } else {
        1
    }

    val message = when (number) {
        0 -> {
            "isDark"
        }

        1 -> {
            "isNotDark"
        }

        else -> {
            "Not valid"
        }
    }

    println("The color code ${getThemeColorCode(isDark)}")
}

fun getThemeColorCode(isDark: Boolean): Int {
    return if (isDark) {
        178128
    } else {
        19101
    }
}

// Classes, data class, enum class, sealed class, object, Interfaces
fun classesAndInterfaces() {
    val person = Person("daniel", "nolasco")
    val person2 = person.copy(lastName = "alvarado")

    println("person: $person")
    println("person2: $person2")

    println("RED color code: ${Colors.RED.colorCode}")

    val color: Colors = Colors.BLUE

    when (color) {
        Colors.RED -> {

        }

        Colors.GREEN -> {

        }

        Colors.BLUE -> {

        }
    }

    val uiState: UIState = UIState.Error("No internet")

    when (uiState) {
        UIState.None -> {
            println("NONE")
        }

        is UIState.Error -> {
            println(uiState.message)
        }

        is UIState.Success -> {
            println((uiState as UIState.Success).data)
        }
    }

    println("MyObject value: ${MyObject.value}")
}

class MyClass() {
    var name = "daniel"
    var lastName = "nolasco"

    override fun toString(): String {
        return "$name $lastName"
    }
}

// Represent data
data class Person(
    val name: String,
    val lastName: String,
) {

}

enum class Colors(val colorCode: Int) {
    RED(9816391),
    GREEN(1209901),
    BLUE(981271);
}

sealed class UIState {
    object None : UIState()
    class Error(val message: String) : UIState()
    data class Success(val data: List<Person>) : UIState()
}

object MyObject {
    val value: String = "hello"
}

// Extension functions and properties
fun extensions() {
    val person = Person(name = "daniel", lastName = "nolasco")

    person.sayName()
    person.screamName("hi")

    println("Full name: ${person.fullName}")
}

fun Person.sayName() {
    println(name)
}

fun Person.screamName(trailing: String) {
    println("${name.uppercase()}$trailing")
}

val Person.fullName get() = "$name $lastName"

// Is the same as: val Person.fullName get() = "$name $lastName"
//fun Person.getFullName(): String {
//    return "$name $lastName"
//}

// Access modifiers, Interfaces and companion objects
fun accessModifiers() {
    val modifiers = Modifiers()

//    println("modifiers.privateValue: ${modifiers.privateValue}")
//    println("modifiers.protectedValue: ${modifiers.protectedValue}")
    println("modifiers.publicValue: ${modifiers.publicValue}")
    println("modifiers.internalValue: ${modifiers.internalValue}")

    val extraModifiers = ExtraModifiers()
    extraModifiers.doSomething()

    val contract: Contract = ContractImpl()
    contract.mustDoIt()

    MyClass2.property
    MyClass2.doSomething()
}

open class Modifiers {
    private val privateValue = "Only modifiers class can use me"
    protected val protectedValue = "Only Modifiers and it's children can use me"
    val publicValue = "Everyone can use me"
    internal val internalValue = "Everyone in this module can use me"
}

class ExtraModifiers : Modifiers() {
    fun doSomething() {
//        println("this.privateValue: $privateValue")
        println("this.protectedValue: $protectedValue")
        println("this.publicValue: $publicValue")
        println("this.internalValue: $internalValue")
    }
}

interface Contract {
    fun mustDoIt()
}

class ContractImpl : Contract {
    override fun mustDoIt() {
        println("Im doing it!")
    }
}

class MyClass2 {

    companion object {
        val property: Int = 0

        fun doSomething() {
            println("My property is: $property")
        }
    }
}

// Scope functions: let, apply, also, etc
fun scopeFunctions() {
    val operations = Operations(initialValue = 10).apply {
        multiply(2)
    }

    println("operations.currentValue: ${operations.currentValue}")

    val result = operations.let {
        println("Will add 30 to ${it.currentValue}")
        it.plus(30)
    }

    println("operations.plus result: $result")

    val result2 = operations.also {
        it.minus(10)
    }.currentValue

    println("operations.minus result: $result2")

    with(operations) {
        println("Starting at value: $currentValue")
        println("Adding 50...")
        plus(50)
        println("dividing by 2...")
        divide(2)
        println("multiplying by 10")
        multiply(10)
        println("Finished at value: $currentValue")
    }

    val operations2 = Operations(0)

    val result3 = operations2.let {
        println("Will add 100 to ${it.currentValue}")
        it.plus(100)
    }.let {
        it * 100
    }

    println("operations2.currentValue: ${operations2.currentValue} - result3: $result3")
}

class Operations(val initialValue: Int) {

    private var _currentValue: Int = initialValue
    val currentValue get() = _currentValue

    fun multiply(value: Int): Int {
        _currentValue = _currentValue * value
//        _currentValue *= value

        return currentValue
    }

    fun divide(value: Int): Int {
        _currentValue = _currentValue / value
//        _currentValue /= value

        return currentValue
    }

    fun plus(value: Int): Int {
        _currentValue = _currentValue + value
//        _currentValue += value

        return currentValue
    }

    fun minus(value: Int): Int {
        _currentValue = _currentValue - value
//        _currentValue -= value

        return currentValue
    }
}

// Operator functions (MONO)
fun operatorFunctions() {
    val operationsEnchanted = OperationsEnchanted(initialValue = 1)

    val result = operationsEnchanted + 100
    println("result of operationsEnchanted + 100: $result")

    val result2 = operationsEnchanted * 2
    println("result of operationsEnchanted * 2: $result2")

    val greetingUseCase = GreetingUseCase(spanish = true)

    greetingUseCase(name = "daniel")
//    greetingUseCase.invoke(name = "daniel")
}

class OperationsEnchanted(val initialValue: Int) {

    private var _currentValue: Int = initialValue
    val currentValue get() = _currentValue

    operator fun times(value: Int): Int {
        _currentValue *= value

        return currentValue
    }

    operator fun plus(value: Int): Int {
        _currentValue += value

        return currentValue
    }
}

class GreetingUseCase(val spanish: Boolean) {

    operator fun invoke(name: String) {
        if (spanish) {
            println("Hola $name")
        } else {
            println("Hello $name")
        }
    }
}

// Exceptions
fun exceptions() {
    try {
        throw MyException()
    } catch (exception: Exception) {
        println("There was an exception: $exception")
    }

    try {
        throw GenericException("An error happened")
    } catch (exception: Exception) {
        println("There was an exception: $exception")
    }

    try {
        throw HttpException(401, "Not authorized")
    } catch (exception: Exception) {
        println("There was an exception: $exception")

        if (exception is HttpException) {
            println("This is an HttpException with code ${exception.code}")
        }
    }
}

class MyException : Exception() {
    override val message = "Something went bad"
}

class GenericException(override val message: String) : Exception() {

}

class HttpException(val code: Int, val error: String) : Exception() {
    override val message = "The request failed with code: $code and error: $error"
}

// Nullables
fun nullables() {
    val nullableString: String? = buildString()

    println("nullableString: $nullableString")

    println("nullableString size: ${nullableString?.length}")

    val string: String = nullableString ?: "N/A"
//    val string: String = nullableString ?: throw GenericException("nullableString was null")

    println("string: $string")

    try {
        val string2: String = nullableString!!
    } catch (exception: NullPointerException) {
        println(exception)
    }
}

fun buildString(): String? {
    return null
}

// delegation and Lazy
fun delegationAndLazy() {
    val program = Program()

    program.version()
    program.useProcessingTools()
}

class Program {
    private val processingTools by lazy {
        ProcessingTools()
    }

    fun version() {
        println("Current version: 1.0.0")
    }

    fun useProcessingTools() {
        processingTools.process()
    }
}

class ProcessingTools {
    init { // Called when the class is instantiated, this block is often used to initialize state in the class hence the name init
        for (i in 0..10) {
            println("This is expensive!")
        }
    }

    fun process() {
        println("I'm processing...")
        println("Done!")
    }
}

interface Printer {
    fun print(message: String)
}

class CanonPrinter : Printer {
    override fun print(message: String) {
        println("Canon Printer : $message")
    }

    operator fun getValue(ref: Any?, kProperty: KProperty<*>): Printer {
        return this
    }
}

class AnotherProgram {
    val printer: Printer by CanonPrinter()
}

// Result (MONO)
fun result() {
    val int = stringToInt("1").fold(
        {
            it * 10
        },
        {
            println("There was an error: $it")
            0
        }
    )

    println("stringToInt: $int")
}

fun stringToInt(string: String): Result<Int> {
    return try {
        val int = string.toInt()

        Result.success(int)
    } catch (exception: NumberFormatException) {
        Result.failure(exception)
    }
}

// Coroutines, suspend, try catch warning
fun coroutines() = runBlocking {
    val coroutineScope = CoroutineScope(SupervisorJob() + Dispatchers.IO)

//    coroutineScope.launch {
//        count10Seconds()
//    }
//
//    coroutineScope.launch {
//        divideMyLuckyNumber()
//    }

//    coroutineScope.launch {
//        try {
//            countSecondsIndefinitely()
//        } catch (exception:Exception) {
//            println(exception)
//        }
//        println("This should not be shown")
//    }

//    coroutineScope.launch {
//        divideMyLuckyNumberSafely()
//        println("This should not be shown")
//    }

    coroutineScope.launch {
        var seconds = 0

        while (isActive) {
            delay(1000)
            seconds += 1
            println("$seconds seconds have passed")
        }
    }

    delay(4000)
    coroutineScope.cancel()

    delay(20000)
}

suspend fun count10Seconds() {
    for (i in 1..10) {
        delay(1000)
        println("$i seconds have passed")
    }
}

suspend fun divideMyLuckyNumber() {
    for (i in 10 downTo 0) {
        delay(500)
        val result = 4 / i
        println("Result of dividing 4 by $i: $result")
    }
}

suspend fun countSecondsIndefinitely() {
    var seconds = 0

    while (true) { // Avoid this, there is always a better solution
        delay(1000)
        seconds += 1
        println("$seconds seconds have passed")
    }
}

suspend fun divideMyLuckyNumberSafely() {
    try {
        for (i in 10 downTo 0) {
            delay(500)
            val result = 4 / i
            println("Result of dividing 4 by $i: $result")
        }
    } catch (exception: Exception) {
        if (exception is CancellationException) {
            throw exception
        }

        println("Error dividing my lucky number: $exception")
    }
}

// Generics
fun generics() {
    val listAggregator = ListAggregator<Int>(listOf(1, 2, 3))

    val newList = listAggregator.append(listOf(1))

    println("newList: $newList")
}

class ListAggregator<T>(list: List<T>) {
    private var currentList = list

    fun append(anotherList: List<T>): List<T> {
        currentList = currentList + anotherList

        return currentList
    }

    fun prepend(anotherList: List<T>): List<T> {
        currentList = anotherList + currentList

        return currentList
    }
}

// Our own scope T.() -> R (MONO)
fun dsl() {
    val rook = Rook()

    println("The employee of the month is...")

    rook.withEmployees {
        iosEmployee()
    }.fold(
        {
            println("Congratulations $it!!!")

            val salary = rook.withEmployees { iosEmployeeSalary() }.getOrThrow()

            println("Your next paycheck will be ${salary + (salary * .05)}")
        },
        {
            println("There was an error: $it")
        }
    )
}

class Rook {
    private val employees = RookEmployees()

    fun <R> withEmployees(block: RookEmployees.() -> R): Result<R> {
        return try {
            val value = block(employees)

            Result.success(value)
        } catch (exception: Exception) {
            Result.failure(exception)
        }
    }
}

class RookEmployees {
    fun iosEmployeeSalary(): Double {
        return 100000.0
    }

    fun reactEmployeeSalary(): Double {
        return 100000.0
    }

    fun androidEmployeeSalary(): Double {
        return 100000.0
    }

    fun flutterEmployeeSalary(): Double {
        return 100000.0
    }

    fun iosEmployee(): String {
        return "Francisco"
    }

    fun reactEmployee(): String {
        return "Javier"
    }

    fun androidEmployee(): String {
        return "Daniel"
    }

    fun flutterEmployee(): String {
        throw EmployeeNotFoundException()
    }
}

class EmployeeNotFoundException : Exception() {
    override val message get() = "This employee does not exists, need to hire one"
}

// Collections
fun collections() {
    val array: Array<Int> = emptyArray()
    // Arrays cant be mutated

    // Ordered list
    val list: List<String> = listOf()
    val mutableList: MutableList<String> = mutableListOf()

    mutableList.add("hello")

    // Dictionary
    val map: Map<Int, String> = emptyMap()
    val mutableMap: MutableMap<Int, String> = mutableMapOf(
        Pair(1, "hello"),
        2 to "world"
    )

    mutableMap[3] = "!!!"

    // Items can be duplicated, not ordered
    val set: Set<String> = emptySet()
    val mutableSet: Set<String> = mutableSetOf()

    mutableSet.plus("hello")
}

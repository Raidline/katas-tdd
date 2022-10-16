fun main(args: Array<String>) {

    val values = 1..100;

    val fizzBuzz = FizzBuzz()

    values.map {
        fizzBuzz.printFromValue(it)
    }.forEach {println(it)}
}

package fizzbuzz

import FizzBuzz
import kotlin.test.Test

internal class FizzBuzzTest {

    private val fizzBuzz = FizzBuzz()


    @Test
    fun `given number 3 should print fizz`() {
        fizzBuzz.printFromValue(3)
    }
}

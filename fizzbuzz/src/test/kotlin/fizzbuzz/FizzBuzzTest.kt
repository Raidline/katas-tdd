package fizzbuzz

import FizzBuzz
import org.amshove.kluent.should
import org.amshove.kluent.shouldBe
import org.amshove.kluent.shouldBeEqualTo
import org.amshove.kluent.shouldBeEqualToIgnoringCase
import utils.UnitTest

internal class FizzBuzzTest {

    private val fizzBuzz = FizzBuzz()


    @UnitTest
    fun `given number 3 should return fizz`() {
        val result = fizzBuzz.printFromValue(3)

        result shouldBeEqualTo "Fizz"
    }

    @UnitTest
    fun `given number 6 should return fizz`() {
        val result = fizzBuzz.printFromValue(6)

        result shouldBeEqualTo "Fizz"
    }

    @UnitTest
    fun `given number 5 should return Buzz`() {
        val result = fizzBuzz.printFromValue(5)

        result shouldBeEqualTo "Buzz"
    }

    @UnitTest
    fun `given number 10 should return Buzz`() {
        val result = fizzBuzz.printFromValue(10)

        result shouldBeEqualTo "Buzz"
    }

    @UnitTest
    fun `given number 15 should return FizzBuzz`() {
        val result = fizzBuzz.printFromValue(15)

        result shouldBeEqualTo "FizzBuzz"
    }

    @UnitTest
    fun `given number 1 should return 1 in string`() {
        val result = fizzBuzz.printFromValue(1)

        result shouldBeEqualTo "1"
    }

    @UnitTest
    fun `given number 2 should return number in string`() {
        val result = fizzBuzz.printFromValue(2)

        result shouldBeEqualTo "2"
    }
}

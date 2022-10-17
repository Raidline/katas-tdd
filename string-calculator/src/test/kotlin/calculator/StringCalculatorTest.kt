package calculator

import org.amshove.kluent.shouldBeEqualTo
import org.junit.jupiter.api.DynamicTest
import utils.ParameterizedTest
import utils.UnitTest


internal class StringCalculatorTest {

    private val calculator = StringCalculator()


    @ParameterizedTest
    fun `given two numbers seperated by comma should return their sum`()  = listOf(
        "1,2" to 3,
        "1,3" to 4,
        "2,3" to 5
    ).map { (input, expected) ->
        DynamicTest.dynamicTest("given $input should return $expected") {
            val result = calculator.calculate(input)

            result shouldBeEqualTo expected
        }
    }

    @UnitTest
    fun `given a single number 1 should return 1`() {
        val result = calculator.calculate("1")

        result shouldBeEqualTo 1
    }

    @UnitTest
    fun `given a single number 2 should return 2`() {
        val result = calculator.calculate("2")

        result shouldBeEqualTo 2
    }

    @UnitTest
    fun `given a double digit number 12 should return 12`() {
        val result = calculator.calculate("12")

        result shouldBeEqualTo 12
    }

    @UnitTest
    fun `given a empty string should return 0`() {
        val result = calculator.calculate("")

        result shouldBeEqualTo 0
    }

}

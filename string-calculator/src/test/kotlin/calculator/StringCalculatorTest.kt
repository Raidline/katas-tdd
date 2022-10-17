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

    @ParameterizedTest
    fun `given three numbers seperated by comma should return their sum`() = listOf(
        "1,1,1" to 3,
        "2,2,2" to 6,
        "1,2,3" to 6
    ).map { (input, excepted) ->
        DynamicTest.dynamicTest("given $input should return $excepted") {
            val result = calculator.calculate(input)

            result shouldBeEqualTo excepted
        }
    }

    @ParameterizedTest
    fun `given four numbers seperated by comma should return their sum`() = listOf(
        "1,1,1,1" to 4,
        "2,2,2,2" to 8,
        "1,2,3,4" to 10
    ).map { (input, excepted) ->
        DynamicTest.dynamicTest("given $input should return $excepted") {
            val result = calculator.calculate(input)

            result shouldBeEqualTo excepted
        }
    }

    @ParameterizedTest
    fun `given a single number should return its value`() = listOf(
        "1" to 1,
        "2" to 2,
        "12" to 12
    ).map { (input, excepted) ->
        DynamicTest.dynamicTest("given $input should return $excepted") {
            val result = calculator.calculate(input)

            result shouldBeEqualTo excepted
        }
    }

    @UnitTest
    fun `given a empty string should return 0`() {
        val result = calculator.calculate("")

        result shouldBeEqualTo 0
    }

}

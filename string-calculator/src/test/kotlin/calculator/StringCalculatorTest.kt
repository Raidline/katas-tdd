package calculator

import calculator.aggregator.StringAggregator
import calculator.aggregator.StringProcessorAggregate
import io.mockk.every
import io.mockk.impl.annotations.InjectMockKs
import io.mockk.impl.annotations.MockK
import io.mockk.junit5.MockKExtension
import io.mockk.mockkObject
import org.amshove.kluent.shouldBeEqualTo
import org.junit.jupiter.api.DynamicTest
import org.junit.jupiter.api.extension.ExtendWith
import utils.ParameterizedTest
import utils.UnitTest

@ExtendWith(MockKExtension::class)
internal class StringCalculatorTest {

    @MockK
    private lateinit var aggregator : StringProcessorAggregate

    @InjectMockKs
    private lateinit var calculator : StringCalculator


    @ParameterizedTest
    fun `given two numbers seperated by comma should return their sum`()  = listOf(
        "1,2" to ParameterizedArgument(3, listOf("1", "2")),
        "1,3" to ParameterizedArgument(4, listOf("1", "3")),
        "2,3" to ParameterizedArgument(5, listOf("2", "3"))
    ).map { (input, params) ->
        DynamicTest.dynamicTest("given $input should return ${params.expected}") {
            every { aggregator.aggregate(input) } returns params.mockedNumbers
            val result = calculator.calculate(input)

            result shouldBeEqualTo params.expected
        }
    }

    @ParameterizedTest
    fun `given three numbers seperated by comma should return their sum`() = listOf(
        "1,1,1" to ParameterizedArgument(3, listOf("1", "1", "1")),
        "2,2,2" to ParameterizedArgument(6, listOf("2", "2", "2")),
        "1,2,3" to ParameterizedArgument(6, listOf("1", "2", "3"))
    ).map { (input, params) ->
        DynamicTest.dynamicTest("given $input should return $params.excepted") {
            every { aggregator.aggregate(input) } returns params.mockedNumbers
            val result = calculator.calculate(input)

            result shouldBeEqualTo params.expected
        }
    }

    @ParameterizedTest
    fun `given four numbers seperated by comma should return their sum`() = listOf(
        "1,1,1,1" to ParameterizedArgument(4, listOf("1", "1", "1", "1")),
        "2,2,2,2" to ParameterizedArgument(8, listOf("2", "2", "2", "2")),
        "1,2,3,4" to ParameterizedArgument(10, listOf("1", "2", "3", "4"))
    ).map { (input, params) ->
        DynamicTest.dynamicTest("given $input should return ${params.expected}") {
            every { aggregator.aggregate(input) } returns params.mockedNumbers
            val result = calculator.calculate(input)

            result shouldBeEqualTo params.expected
        }
    }

    @ParameterizedTest
    fun `given a single number should return its value`() = listOf(
        "1" to ParameterizedArgument(1, listOf("1")),
        "2" to ParameterizedArgument(2, listOf("2")),
        "12" to ParameterizedArgument(12, listOf("12"))
    ).map { (input, params) ->
        DynamicTest.dynamicTest("given $input should return $params.excepted") {
            every { aggregator.aggregate(input) } returns params.mockedNumbers
            val result = calculator.calculate(input)

            result shouldBeEqualTo params.expected
        }
    }

    @UnitTest
    fun `given a empty string should return 0`() {
        val result = calculator.calculate("")

        result shouldBeEqualTo 0
    }

    @ParameterizedTest
    fun `given two numbers seperated by newline should return their sum`() = listOf(
        "1\n2" to ParameterizedArgument(3, listOf("1", "2")),
        "4\n4" to ParameterizedArgument(8, listOf("4", "4"))
    ).map { (input, params) ->
        DynamicTest.dynamicTest("given $input seperated by newline should return $params.expected") {
            every { aggregator.aggregate(input) } returns params.mockedNumbers
            val result = calculator.calculate(input)

            result shouldBeEqualTo params.expected
        }
    }

    @ParameterizedTest
    fun `given three numbers seperated by newline and comma should return their sum`() = listOf(
        "1\n2,3" to ParameterizedArgument(6, listOf("1", "2", "3")),
        "4\n4,1" to ParameterizedArgument(9, listOf("4", "4", "1")),
        "1,2\n3" to ParameterizedArgument(6, listOf("1", "2", "3")),
        "2,2\n3" to ParameterizedArgument(7, listOf("2", "2", "3"))
    ).map { (input, params) ->
        DynamicTest.dynamicTest("given $input seperated by newline and comma should return $params.expected") {
            every { aggregator.aggregate(input) } returns params.mockedNumbers
            val result = calculator.calculate(input)

            result shouldBeEqualTo params.expected
        }
    }

    private data class ParameterizedArgument(val expected : Int, val mockedNumbers : List<String>)
}

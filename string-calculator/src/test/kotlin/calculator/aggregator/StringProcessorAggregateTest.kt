package calculator.aggregator

import calculator.processor.StringCommaInputProcessor
import calculator.processor.StringNewLineInputProcessor
import io.mockk.impl.annotations.InjectMockKs
import io.mockk.impl.annotations.MockK
import org.amshove.kluent.shouldBeEqualTo
import org.amshove.kluent.shouldContainAll
import org.amshove.kluent.shouldHaveSize
import org.junit.jupiter.api.DynamicTest
import utils.ParameterizedTest
import utils.UnitTest

internal class StringProcessorAggregateTest {

    @MockK
    private lateinit var commaInputProcessor: StringCommaInputProcessor

    @MockK
    private lateinit var newLineInputProcessor: StringNewLineInputProcessor

    @InjectMockKs
    private lateinit var aggregator : StringProcessorAggregate

    @ParameterizedTest
    fun `given numbers seperated by comma should return list containing values`() = listOf(
        "1,2" to listOf("1","2"),
        "2,2" to listOf("2", "2"),
        "3,2" to listOf("3", "2")
    ).map { (input, expected) ->
        DynamicTest.dynamicTest("given $input seperated by comma should return $expected") {
            val result = aggregator.aggregate(input)

            result shouldHaveSize 2
            result shouldBeEqualTo expected
        }
    }

    @ParameterizedTest
    fun `given numbers seperated by newline should return list containing values`() = listOf(
        "1\n2" to listOf("1","2"),
        "2\n2" to listOf("2", "2"),
        "3\n2" to listOf("3", "2")
    ).map { (input, expected) ->
        DynamicTest.dynamicTest("given $input seperated by newline should return $expected") {
            val result = aggregator.aggregate(input)

            result shouldHaveSize 2
            result shouldBeEqualTo expected
        }
    }

    @ParameterizedTest
    fun `given numbers seperated by newline and comma should return list containing values`() = listOf(
        "1\n2,3" to listOf("1","2", "3"),
        "2\n2,3" to listOf("2", "2", "3"),
        "3\n2,4" to listOf("3", "2", "4")
    ).map { (input, expected) ->
        DynamicTest.dynamicTest("given $input seperated by newline and comma should return $expected") {
            val result = aggregator.aggregate(input)

            result shouldHaveSize 3
            result shouldBeEqualTo expected
        }
    }
}

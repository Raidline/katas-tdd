package calculator.aggregator

import calculator.processor.StringCommaInputProcessor
import calculator.processor.StringNewLineInputProcessor
import io.mockk.every
import io.mockk.impl.annotations.InjectMockKs
import io.mockk.impl.annotations.MockK
import io.mockk.junit5.MockKExtension
import org.amshove.kluent.shouldBeEqualTo
import org.amshove.kluent.shouldContainAll
import org.amshove.kluent.shouldHaveSize
import org.junit.jupiter.api.DynamicTest
import org.junit.jupiter.api.extension.ExtendWith
import utils.ParameterizedTest
import utils.UnitTest

@ExtendWith(MockKExtension::class)
internal class StringProcessorAggregateTest {

    @MockK
    private lateinit var commaInputProcessor: StringCommaInputProcessor

    @MockK
    private lateinit var newLineInputProcessor: StringNewLineInputProcessor

    @InjectMockKs
    private lateinit var aggregator : StringProcessorAggregate

    @ParameterizedTest
    fun `given numbers seperated by comma should return list containing values`() = listOf(
        "1,2" to ParameterizedArgument(listOf("1","2")),
        "2,2" to ParameterizedArgument(listOf("2", "2")),
        "3,2" to ParameterizedArgument(listOf("3", "2"))
    ).map { (input, expected) ->
        DynamicTest.dynamicTest("given $input seperated by comma should return ${expected.result}") {
            every { commaInputProcessor.canHandle(input) } returns true
            every { newLineInputProcessor.canHandle(any()) } returns false
            every { commaInputProcessor.process(input) } returns expected.result

            val result = aggregator.aggregate(input)

            result shouldHaveSize 2
            result shouldBeEqualTo expected.result
        }
    }

    @ParameterizedTest
    fun `given numbers seperated by newline should return list containing values`() = listOf(
        "1\n2" to ParameterizedArgument(listOf("1","2")),
        "2\n2" to ParameterizedArgument(listOf("2", "2")),
        "3\n2" to ParameterizedArgument(listOf("3", "2"))
    ).map { (input, expected) ->
        DynamicTest.dynamicTest("given $input seperated by newline should return ${expected.result}") {
            every { newLineInputProcessor.process(input) } returns expected.result
            every { commaInputProcessor.canHandle(input) } returns false
            every { newLineInputProcessor.canHandle(any()) } returns true
            val result = aggregator.aggregate(input)

            result shouldHaveSize 2
            result shouldBeEqualTo expected.result
        }
    }

    @ParameterizedTest
    fun `given numbers seperated by newline and comma should return list containing values`() = listOf(
        "1\n2,3" to ParameterizedArgument(listOf("1","2", "3"), cantHandleInput = "3", canHandleInput = "1\n2", commaMocked = listOf("1\n2", "3"),
            newLineMocked = listOf("1", "2")),
        "2\n2,3" to ParameterizedArgument(listOf("2", "2", "3"), cantHandleInput = "3", canHandleInput = "2\n2", commaMocked = listOf("2\n2", "3"),
            newLineMocked = listOf("2", "2")),
        "3\n2,4" to ParameterizedArgument(listOf("3", "2", "4"), cantHandleInput = "4", canHandleInput = "3\n2", commaMocked = listOf("3\n2" ,"4"),
            newLineMocked = listOf("3", "2"))
    ).map { (input, expected) ->
        DynamicTest.dynamicTest("given $input seperated by newline and comma should return ${expected.result}") {
            every { commaInputProcessor.process(input) } returns expected.commaMocked
            every { newLineInputProcessor.process(any()) } returns expected.newLineMocked

            every { commaInputProcessor.canHandle(input) } returns true
            every { newLineInputProcessor.canHandle(expected.canHandleInput) } returns true
            every { newLineInputProcessor.canHandle(expected.cantHandleInput) } returns false
            val result = aggregator.aggregate(input)

            result shouldHaveSize 3
            result shouldBeEqualTo expected.result
        }
    }

    @UnitTest
    fun `given a new default delimiter when aggregating should return list containing values`() {
        val input = "//;\n1;2"

        every { commaInputProcessor.canHandle(input) } returns false
        every { newLineInputProcessor.canHandle(any()) } returns false

        val result = aggregator.aggregate(input)

        result shouldHaveSize 2
        result shouldBeEqualTo listOf("1","2")

    }

    @UnitTest
    fun `given single number should return list with number`() {
        val result = aggregator.aggregate("1")

        result shouldBeEqualTo listOf("1")
    }

    private data class ParameterizedArgument(val result : List<String>,
                                             val canHandleInput : String = "",
                                             val cantHandleInput : String = "",
                                             val commaMocked : List<String> = listOf(),
                                             val newLineMocked : List<String> = listOf())
}

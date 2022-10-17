package calculator.processor

import org.amshove.kluent.shouldBeEqualTo
import org.amshove.kluent.shouldContainAll
import org.amshove.kluent.shouldHaveSize
import utils.UnitTest

class StringNewLineInputProcessorTest {

    private val processor = StringNewLineInputProcessor()

    @UnitTest
    fun `given two numbers seperated by newline should return list of strings containing all numbers`() {
        val result = processor.process("1\n2")

        result shouldHaveSize 2
        result shouldContainAll listOf<String>("1","2")
    }

    @UnitTest
    fun `given three numbers seperated by newline should return list of strings containing all numbers`() {
        val result = processor.process("1\n2\n3")

        result shouldHaveSize 3
        result shouldContainAll listOf<String>("1", "2", "3")
    }

    @UnitTest
    fun `given newline seperated string should be able to handle`() {
        val canHandle = processor.canHandle("1\n2")

        canHandle shouldBeEqualTo true
    }
}

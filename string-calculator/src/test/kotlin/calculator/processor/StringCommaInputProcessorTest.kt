package calculator.processor

import org.amshove.kluent.shouldBeEqualTo
import org.amshove.kluent.shouldContainAll
import org.amshove.kluent.shouldHaveSize
import utils.UnitTest

class StringCommaInputProcessorTest {

    private val processor = StringCommaInputProcessor()

    @UnitTest
    fun `given two numbers seperated by comma should return list of strings containing all numbers`() {
        val result = processor.process("1,2")

        result shouldHaveSize 2
        result shouldContainAll listOf<String>("1", "2")
    }

    @UnitTest
    fun `given three numbers seperated by comma should return list of strings containing all numbers`() {
        val result = processor.process("1,2,3")

        result shouldHaveSize 3
        result shouldContainAll listOf<String>("1", "2", "3")
    }

    @UnitTest
    fun `given comma seperated string should be able to handle`() {
        val canHandle = processor.canHandle("1,2")

        canHandle shouldBeEqualTo true
    }
}

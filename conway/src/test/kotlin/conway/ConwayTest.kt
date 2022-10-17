package conway

import conway.state.ConwayCellState
import org.amshove.kluent.shouldBeEqualTo
import org.amshove.kluent.shouldBeInstanceOf
import org.amshove.kluent.shouldNotBe
import org.junit.jupiter.api.assertThrows
import util.UnitTest

internal class ConwayTest {

    private val conway = Conway()

    @UnitTest
    fun `given a cell state and neighbours executes cell behaviour`() {
        val state = conway.execute(ConwayCellState.ALIVE, 2)

        state shouldNotBe null
    }


    @UnitTest
    fun `given a unknown cell state and neighbours throws exception`() {
        val exception = assertThrows<IllegalArgumentException> { conway.execute(ConwayCellState.UNKNOWN, 2) }

        exception shouldBeInstanceOf IllegalArgumentException::class
        exception.message shouldBeEqualTo "Oops no state found"
    }
}

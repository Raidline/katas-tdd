package conway.behaviour

import conway.state.ConwayCellState
import org.amshove.kluent.shouldBeEqualTo
import org.junit.jupiter.api.DynamicTest
import util.ParameterizedUnitTests
import util.UnitTest


class DeadConwayCellTest {

    private val deadCell = DeadConwayCell

    @ParameterizedUnitTests
    fun `given a dead cell with less than 3 neighbours it stays dead`() = listOf(
        0,
        1,
        2
    ).map { input ->
        DynamicTest.dynamicTest("given a dead cell with $input neighbours dies") {
            val cellState = deadCell.behave(input)
            cellState shouldBeEqualTo ConwayCellState.DEAD
        }
    }

    @ParameterizedUnitTests
    fun `given a dead cell with more than 3 neighbours it stays dead`() = listOf(
        4,
        5,
        6,
        7
    ).map { input ->
        DynamicTest.dynamicTest("given a dead cell with $input neighbours dies") {
            val cellState = deadCell.behave(input)
            cellState shouldBeEqualTo ConwayCellState.DEAD
        }
    }

    @UnitTest
    fun `given a dead cell with 3 neighbours revives`() {
        val cellState = deadCell.behave(3)

        cellState shouldBeEqualTo ConwayCellState.ALIVE
    }
}

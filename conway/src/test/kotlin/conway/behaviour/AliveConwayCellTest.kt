package conway.behaviour

import conway.state.ConwayCellState
import org.amshove.kluent.shouldBeEqualTo
import org.junit.jupiter.api.DynamicTest
import util.ParameterizedUnitTests


internal class AliveConwayCellTest {

    private val aliveCell = AliveConwayCell

    @ParameterizedUnitTests
    fun `given a living cell with less than 2 neighbours dies`() = listOf(
        0,
        1
    ).map { input ->
        DynamicTest.dynamicTest("given a living cell with $input neighbours dies") {
            val cellState = aliveCell.behave(input)
            cellState shouldBeEqualTo ConwayCellState.DEAD
        }
    }

    @ParameterizedUnitTests
    fun `given a living cell with 2 or 3 neighbours stays alive`() = listOf(
        2,
        3
    ).map { input ->
        DynamicTest.dynamicTest("given a living cell with $input neighbours lives") {
            val cellState = aliveCell.behave(input)
            cellState shouldBeEqualTo ConwayCellState.ALIVE
        }
    }

    @ParameterizedUnitTests
    fun `given a living cell with more than 3 neighbours dies`() = listOf(
        4,
        5,
        6
    ).map { input ->
        DynamicTest.dynamicTest("given a living cell with $input neighbours dies") {
            val cellState = aliveCell.behave(input)
            cellState shouldBeEqualTo ConwayCellState.DEAD
        }
    }
}

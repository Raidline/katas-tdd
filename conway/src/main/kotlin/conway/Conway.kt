package conway

import conway.behaviour.AliveConwayCell
import conway.behaviour.DeadConwayCell
import conway.state.ConwayCellState

// A living cell with less than 2 neighbours dies
// A living cell with 2 or 3 neighbours lives
// A living cell with more than 3 neighbours dies
// A dead cell with 3 neighbours revives

class Conway {

    private val cellPossibleStates = mapOf(
        ConwayCellState.DEAD to DeadConwayCell,
        ConwayCellState.ALIVE to AliveConwayCell
    )

    fun execute(cellState: ConwayCellState, neighbours : Int) : ConwayCellState =
        cellPossibleStates[cellState]?.behave(neighbours) ?: throw IllegalArgumentException("Oops no state found")
}

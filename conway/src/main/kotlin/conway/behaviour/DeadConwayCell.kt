package conway.behaviour

import conway.state.ConwayCellState

object DeadConwayCell : ConwayCellBehaviour {

    override fun behave(neighbours: Int): ConwayCellState {
        if (neighbours == 3) {
            return ConwayCellState.ALIVE
        }

        return ConwayCellState.DEAD
    }
}

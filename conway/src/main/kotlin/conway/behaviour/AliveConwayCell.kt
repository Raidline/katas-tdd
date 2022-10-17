package conway.behaviour

import conway.state.ConwayCellState

/**
 * @author
 **/
object AliveConwayCell : ConwayCellBehaviour {

    override fun behave(neighbours: Int): ConwayCellState {
        if (neighbours < 2 || neighbours > 3) {
            return ConwayCellState.DEAD
        }

        return ConwayCellState.ALIVE
    }
}

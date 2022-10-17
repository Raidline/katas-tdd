package conway.behaviour

import conway.state.ConwayCellState

sealed interface ConwayCellBehaviour {
    fun behave(neighbours : Int) : ConwayCellState
}

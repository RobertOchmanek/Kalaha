package processor.state;

import interfaces.KalahaState.GameStates;
import processor.TurnProcessor;

import static interfaces.KalahaState.GameStates.AFTER_PLAYER1_TURN;

public class AfterFirstState extends GameState {

    public AfterFirstState(TurnProcessor turnContext) {
        super(turnContext);
    }

    @Override
    public void processTurn() {

    }

    @Override
    public GameStates getStateType() {
        return AFTER_PLAYER1_TURN;
    }
}

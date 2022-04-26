package processor.state;

import interfaces.KalahaState.GameStates;
import processor.TurnProcessor;

import static interfaces.KalahaState.GameStates.AFTER_PLAYER2_TURN;

public class AfterSecondState extends GameState {

    public AfterSecondState(TurnProcessor turnContext) {
        super(turnContext);
    }

    @Override
    public void processTurn() {

    }

    @Override
    public GameStates getStateType() {
        return AFTER_PLAYER2_TURN;
    }
}

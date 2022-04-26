package processor.state;

import interfaces.KalahaState.GameStates;
import processor.TurnProcessor;

public abstract class GameState {

    protected final TurnProcessor turnContext;

    public GameState(TurnProcessor turnContext) {
        this.turnContext = turnContext;
    }

    public abstract void processTurn();

    public abstract GameStates getStateType();
}

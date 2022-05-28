package processor.state;

import interfaces.KalahaState;
import processor.TurnProcessor;

import static processor.state.StateName.INITIAL;

public class InitialState extends GameState {

    public InitialState(TurnProcessor turnContext) {
        super(turnContext);
    }

    @Override
    public void processTurn() {
        turnContext.notifyObservers(generateState(turnContext.getGameBoard().getImmutableValues(), KalahaState.GameStates.INITAL_STATE, KalahaState.GameResults.UNKNOWN));
        baseMove();
    }

    @Override
    public StateName getStateName() {
        return INITIAL;
    }
}

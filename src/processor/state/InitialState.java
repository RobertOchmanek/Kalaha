package processor.state;

import interfaces.KalahaState.GameStates;
import processor.TurnProcessor;

import static interfaces.KalahaState.GameStates.INITAL_STATE;

public class InitialState extends GameState {

    public InitialState(TurnProcessor turnContext) {
        super(turnContext);
    }

    @Override
    public void processTurn() {
        System.out.println("Initializing...");
        turnContext.changeState(new EndState(turnContext));
    }

    @Override
    public GameStates getStateType() {
        return INITAL_STATE;
    }
}

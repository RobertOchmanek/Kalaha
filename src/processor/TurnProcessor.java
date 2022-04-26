package processor;

import processor.state.GameState;
import processor.state.InitialState;

public class TurnProcessor {

    private GameState currentState;

    public TurnProcessor() {
        this.currentState = new InitialState(this);
    }

    public void changeState(GameState nextState) {
        currentState = nextState;
    }

    public void processTurn() {
        currentState.processTurn();
    }
}

package processor;

import observers.ObserversManager;
import players.PlayersManager;
import processor.state.GameState;
import processor.state.InitialState;

public class TurnProcessor {

    private GameState currentState;
    private ObserversManager observersManager;
    private PlayersManager playersManager;

    public TurnProcessor() {
        this.currentState = new InitialState(this);
    }

    public void changeState(GameState nextState) {
        currentState = nextState;
    }

    public void processTurn() {
        currentState.processTurn();
    }

    public void initializeBoard(int houses, int seeds) {

    }

    public void setObserversManager(ObserversManager observersManager) {
        this.observersManager = observersManager;
    }

    public void setPlayersManager(PlayersManager playersManager) {
        this.playersManager = playersManager;
    }
}

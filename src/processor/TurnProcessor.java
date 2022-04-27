package processor;

import observers.ObserversManager;
import players.PlayersManager;
import processor.state.GameState;
import processor.state.InitialState;

import java.util.LinkedHashMap;
import java.util.Map;

public class TurnProcessor {

    private final Map<Integer, Integer> board;
    private int numHouses;
    private GameState currentState;
    private ObserversManager observersManager;
    private PlayersManager playersManager;

    public TurnProcessor() {
        this.currentState = new InitialState(this);
        //TODO: sprawdzić czy któraś mapa zwraca w odpowiedniej kolejnosci
        this.board = new LinkedHashMap<>();
    }

    public void changeState(GameState nextState) {
        currentState = nextState;
    }

    public void processTurn() {
        currentState.processTurn();
    }

    public void initializeBoard(int houses, int seeds) {
        this.numHouses = houses;
        int totalFields = (2 * houses) + 2;
        for (int field = 0; field < totalFields; ++field) {
            if (field == houses || field == (2 * houses) + 1) {
                board.put(field, 0);
            } else {
                board.put(field, seeds);
            }
        }
    }

    public Map<Integer, Integer> getBoard() {
        return board;
    }

    public int getNumHouses() {
        return numHouses;
    }

    public void setObserversManager(ObserversManager observersManager) {
        this.observersManager = observersManager;
    }

    public ObserversManager getObserversManager() {
        return observersManager;
    }

    public void setPlayersManager(PlayersManager playersManager) {
        this.playersManager = playersManager;
    }

    public PlayersManager getPlayersManager() {
        return playersManager;
    }
}

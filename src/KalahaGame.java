import interfaces.GameStateObserver;
import interfaces.Kalah;
import interfaces.KalahPlayer;
import interfaces.KalahaState;
import processor.TurnProcessor;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class KalahaGame implements Kalah {

    private final TurnProcessor turnProcessor;

    private final List<GameStateObserver> observers;

    private final List<Integer> playerOnePitsState;

    private KalahPlayer firstPlayer;

    private KalahPlayer secondPlayer;

    public KalahaGame() {
        this.turnProcessor = new TurnProcessor();
        this.observers = new ArrayList<>();
        this.playerOnePitsState = new ArrayList<>();
        this.firstPlayer = null;
        this.secondPlayer = null;
    }

    @Override
    public void setVariant(int houses, int seeds) {
        for (int i = 0; i < houses; i++) {
            playerOnePitsState.add(seeds);
        }
    }

    @Override
    public void registerPlayer(KalahPlayer player) {
        if (Objects.isNull(firstPlayer)) {
            firstPlayer = player;
        } else {
            secondPlayer = player; //if more invokes than 2, set new second player
        }
    }

    @Override
    public void addObserver(GameStateObserver observer) {
        observers.add(observer);
    }

    @Override
    public void startGame() {
        turnProcessor.processTurn();
        turnProcessor.processTurn();
    }

    public void notifyObservers(KalahaState kalahaState) {
        for (GameStateObserver observer : observers) {
            observer.currentState(kalahaState);
        }
    }

    public List<Integer> getPitsState() {
        return playerOnePitsState;
    }
}

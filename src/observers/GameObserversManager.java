package observers;

import interfaces.GameStateObserver;
import interfaces.KalahaState;

import java.util.ArrayList;
import java.util.List;

public class GameObserversManager implements ObserversManager {

    private final List<GameStateObserver> gameObservers;

    public GameObserversManager() {
        this.gameObservers = new ArrayList<>();
    }

    @Override
    public void notifyObservers(KalahaState kalahaState) {
        for (GameStateObserver observer : gameObservers) {
            observer.currentState(kalahaState);
        }
    }

    @Override
    public void addObserver(GameStateObserver observer) {
        gameObservers.add(observer);
    }
}

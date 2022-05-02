package observers;

import interfaces.GameStateObserver;
import interfaces.KalahaState;

import java.util.ArrayList;
import java.util.List;

public class ObserversManager {

    private final List<GameStateObserver> gameObservers;

    public ObserversManager() {
        this.gameObservers = new ArrayList<>();
    }

    public void notifyObservers(KalahaState kalahaState) {
        for (GameStateObserver observer : gameObservers) {
            observer.currentState(kalahaState);
        }
    }

    public void addObserver(GameStateObserver observer) {
        gameObservers.add(observer);
    }

    public void unregisterObservers() {
        gameObservers.clear();
    }
}

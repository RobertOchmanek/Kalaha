package observers;

import interfaces.GameStateObserver;
import interfaces.KalahaState;

public interface ObserversManager {

    void notifyObservers(KalahaState kalahaState);

    void addObserver(GameStateObserver observers);
}

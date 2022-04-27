import interfaces.GameStateObserver;
import interfaces.Kalah;
import interfaces.KalahPlayer;
import observers.GameObserversManager;
import processor.TurnProcessor;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class KalahaGame implements Kalah {

    private final TurnProcessor turnProcessor;
    private final GameObserversManager gameObserversManager;
    private KalahPlayer firstPlayer;
    private KalahPlayer secondPlayer;


    public KalahaGame() {
        this.turnProcessor = new TurnProcessor();
        this.gameObserversManager = new GameObserversManager();
        this.firstPlayer = null;
        this.secondPlayer = null;
    }

    @Override
    public void setVariant(int houses, int seeds) {
        turnProcessor.initializeBoard(houses, seeds);
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
        gameObserversManager.addObserver(observer);
    }

    @Override
    public void startGame() {
        turnProcessor.setObserversManager(gameObserversManager);
    }
}

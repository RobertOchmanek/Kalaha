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

    private final List<Integer> playerOnePitsState;

    private KalahPlayer firstPlayer;

    private KalahPlayer secondPlayer;

    private final GameObserversManager gameObserversManager;

    public KalahaGame() {
        this.turnProcessor = new TurnProcessor();
        this.playerOnePitsState = new ArrayList<>();
        this.firstPlayer = null;
        this.secondPlayer = null;
        this.gameObserversManager = new GameObserversManager();
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
        gameObserversManager.addObserver(observer);
    }

    @Override
    public void startGame() {
        turnProcessor.setObserversManager(gameObserversManager);
        turnProcessor.processTurn();
        turnProcessor.processTurn();
    }

    public List<Integer> getPitsState() {
        return playerOnePitsState;
    }
}

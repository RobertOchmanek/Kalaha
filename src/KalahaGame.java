import interfaces.GameStateObserver;
import interfaces.Kalah;
import interfaces.KalahPlayer;
import observers.ObserversManager;
import players.PlayersManager;
import processor.TurnProcessor;

public class KalahaGame implements Kalah {

    private final TurnProcessor turnProcessor;
    private final ObserversManager observersManager;
    private final PlayersManager playersManager;

    public KalahaGame() {
        this.turnProcessor = new TurnProcessor();
        this.observersManager = new ObserversManager();
        this.playersManager = new PlayersManager();
    }

    @Override
    public void setVariant(int houses, int seeds) {
        turnProcessor.initializeBoard(houses, seeds);
    }

    @Override
    public void registerPlayer(KalahPlayer player) {
        playersManager.registerPlayer(player);
    }

    @Override
    public void addObserver(GameStateObserver observer) {
        observersManager.addObserver(observer);
    }

    @Override
    public void startGame() {
        turnProcessor.setObserversManager(observersManager);
        turnProcessor.setPlayersManager(playersManager);
        //TODO: start game processing
    }
}

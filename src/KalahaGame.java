import interfaces.GameStateObserver;
import interfaces.Kalah;
import interfaces.KalahPlayer;
import observers.GameObserversManager;
import players.GamePlayersManager;
import players.PlayersManager;
import processor.TurnProcessor;

public class KalahaGame implements Kalah {

    private final TurnProcessor turnProcessor;
    private final GameObserversManager gameObserversManager;
    private final PlayersManager playersManager;


    public KalahaGame() {
        this.turnProcessor = new TurnProcessor();
        this.gameObserversManager = new GameObserversManager();
        this.playersManager = new GamePlayersManager();
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
        gameObserversManager.addObserver(observer);
    }

    @Override
    public void startGame() {
        turnProcessor.setObserversManager(gameObserversManager);
        turnProcessor.setPlayersManager(playersManager);
    }
}

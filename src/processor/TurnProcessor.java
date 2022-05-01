package processor;

import board.GameBoard;
import interfaces.KalahaState;
import interfaces.KalahaState.GameResults;
import observers.ObserversManager;
import players.Player;
import players.PlayersManager;
import processor.state.GameState;
import processor.state.InitialState;
import validator.GameValidator;

import java.util.List;

public class TurnProcessor {

    private final GameValidator gameValidator;
    private GameState currentState;
    private GameBoard gameBoard;
    private PlayersManager playersManager;
    private ObserversManager observersManager;

    public TurnProcessor() {
        this.gameValidator = new GameValidator();
        this.currentState = new InitialState(this);
    }

    public void changeState(GameState nextState) {
        currentState = nextState;
    }

    public void processTurn() {
        currentState.processTurn();
    }

    public void initializeBoard(int houses, int seeds) {
        gameBoard = new GameBoard(houses, seeds);
    }

    public GameBoard getGameBoard() {
        return gameBoard;
    }

    public boolean canMove() {
        return gameValidator.canMove(gameBoard);
    }

    public GameResults getGameResult() {
        return gameValidator.getGameResult(gameBoard);
    }

    public void setPlayersManager(PlayersManager playersManager) {
        this.playersManager = playersManager;
    }

    public int requestForMove(Player player, List<Integer> pitsState) {
        return playersManager.requestForMove(player, pitsState);
    }

    public void setObserversManager(ObserversManager observersManager) {
        this.observersManager = observersManager;
    }

    public void notifyObservers(KalahaState gameState) {
        observersManager.notifyObservers(gameState);
    }
}

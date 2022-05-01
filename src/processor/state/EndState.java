package processor.state;

import board.GameBoard;
import interfaces.KalahaState.GameResults;
import players.Player;
import processor.TurnProcessor;

import java.util.Map;

import static interfaces.KalahaState.GameStates.END_OF_GAME;
import static players.Player.FIRST;

public class EndState extends GameState {

    private final Player loosingPlayer;

    public EndState(TurnProcessor turnContext, Player loosingPlayer) {
        super(turnContext);
        this.loosingPlayer = loosingPlayer;
    }

    @Override
    public void processTurn() {
        GameBoard gameBoard = turnContext.getGameBoard();
        finalMove(FIRST.equals(loosingPlayer) ? 0 : gameBoard.getNumHouses() + 1, gameBoard);
        GameResults gameResult = turnContext.getGameResult();
        turnContext.notifyObservers(generateState(gameBoard.getImmutableValues(), END_OF_GAME, gameResult));
        //TODO: do we need to unregister observers to be compliant with the pattern (even simple .remove())?
    }

    private void finalMove(int house, GameBoard gameBoard) {
        Map<Integer, Integer> boardMap = gameBoard.boardAsMap();

        int base = FIRST.equals(loosingPlayer) ? gameBoard.getFirstBase() : gameBoard.getSecondBase();
        int leftoverSeeds = 0;

        //Accumulate sum of leftover seeds for loosing player
        for (int currentHouse = house; currentHouse < base; ++currentHouse) {
            leftoverSeeds += boardMap.put(currentHouse, 0);
        }

        //Sum leftover seeds with players score and insert into base
        leftoverSeeds += boardMap.get(base);
        boardMap.put(base, leftoverSeeds);
    }
}

package processor.state;

import board.GameBoard;
import interfaces.KalahaState;
import interfaces.KalahaState.GameResults;
import interfaces.KalahaState.GameStates;
import processor.TurnProcessor;

import java.util.List;
import java.util.Map;

import static interfaces.KalahaState.GameResults.UNKNOWN;
import static interfaces.KalahaState.GameStates.AFTER_PLAYER1_TURN;
import static players.Player.FIRST;

public abstract class GameState {

    protected final TurnProcessor turnContext;

    public GameState(TurnProcessor turnContext) {
        this.turnContext = turnContext;
    }

    public abstract void processTurn();

    protected void baseMove() {
        GameBoard gameBoard = turnContext.getGameBoard();

        int house = -1;
        while (!validateMove(house, gameBoard)) {
            house = turnContext.requestForMove(FIRST, gameBoard.getImmutableValues());
        }

        boolean additionalMove = makeMove(house, gameBoard);

        //Game result is unknown at this point as it is determined at the beginning of players move
        turnContext.notifyObservers(generateState(gameBoard.getImmutableValues(), AFTER_PLAYER1_TURN, UNKNOWN));

        //If player is eligible for additional stay in current state, else transition to opponents state
        if (!additionalMove) {
            turnContext.changeState(new SecondPlayerState(turnContext));
        }
    }

    protected boolean makeMove(int house, GameBoard gameBoard) {
        Map<Integer, Integer> boardMap = gameBoard.boardAsMap();

        int seeds = boardMap.put(house, 0);

        for (int move = 1; move <= seeds; ++move) {
            //Use modulo in case number of seeds is greater than board size
            int currentHouse = (house + move) % gameBoard.getSize();
            int currentSeeds = boardMap.get(currentHouse);
            boardMap.put(currentHouse, currentSeeds + 1);
        }

        boolean gotBonusSeeds = getBonusSeeds(house, seeds, gameBoard);

        //If player got bonus seeds the finishing position is in one of their houses thus is not eligible for additional move
        if (gotBonusSeeds) {
            return false;
        } else {
            //TODO: replace additional move check with validator step?
            //Check whether last seed was placed in a base and player is eligible for additional turn
            return (house + seeds) % gameBoard.getSize() == gameBoard.getFirstBase();
        }
    }

    protected boolean getBonusSeeds(int house, int seeds, GameBoard gameBoard) {
        return false;
    }

    protected boolean validateMove(int house, GameBoard gameBoard) {
        return house >= 0 && house < gameBoard.getNumHouses() && gameBoard.boardAsMap().get(house) != 0;
    }

    protected KalahaState generateState(List<Integer> pitsState, GameStates gameState, GameResults gameResult) {

        return new KalahaState() {

            @Override
            public List<Integer> getPitsState() {
                return pitsState;
            }

            @Override
            public GameStates getGameState() {
                return gameState;
            }

            @Override
            public GameResults getGameResult() {
                return gameResult;
            }
        };
    }
}

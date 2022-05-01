package processor.state;

import board.GameBoard;
import interfaces.KalahaState;
import interfaces.KalahaState.GameResults;
import interfaces.KalahaState.GameStates;
import players.Player;
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
        while (!turnContext.validateMove(FIRST, house, gameBoard)) {
            house = turnContext.requestForMove(FIRST, gameBoard.getImmutableValues());
        }

        boolean additionalMove = makeMove(house, gameBoard, FIRST);

        //Game result is unknown at this point as it is determined at the beginning of players move
        turnContext.notifyObservers(generateState(gameBoard.getImmutableValues(), AFTER_PLAYER1_TURN, UNKNOWN));

        //If player is eligible for additional move stay in current state, else transition to opponents state
        if (!additionalMove) {
            turnContext.changeState(new SecondPlayerState(turnContext));
        }
    }

    protected boolean makeMove(int house, GameBoard gameBoard, Player player) {
        Map<Integer, Integer> boardMap = gameBoard.boardAsMap();

        int seeds = boardMap.put(house, 0);

        for (int move = 1; move <= seeds; ++move) {
            //Use modulo in case number of seeds is greater than board size
            int currentHouse = (house + move) % gameBoard.getSize();
            int currentSeeds = boardMap.get(currentHouse);
            boardMap.put(currentHouse, currentSeeds + 1);
        }

        //TODO: replace additional move checks with validator step?
        boolean gotBonusSeeds = getBonusSeeds(house, seeds, gameBoard, player);

        /*If player got bonus seeds the finishing position is in one of their houses thus is not eligible for additional move
          Else, heck whether last seed was placed in a base and player is eligible for additional turn*/
        if (gotBonusSeeds) {
            return false;
        } else if (FIRST.equals(player)) {
            return (house + seeds) % gameBoard.getSize() == gameBoard.getFirstBase();
        } else {
            return (house + seeds) % gameBoard.getSize() == gameBoard.getSecondBase();
        }
    }

    protected boolean getBonusSeeds(int house, int seeds, GameBoard gameBoard, Player player) {
        Map<Integer, Integer> boardMap = gameBoard.boardAsMap();

        //Check whether last seed was placed in one of players houses and if so, obtain bonus seeds from opposing house
        int finalPosition = (house + seeds) % gameBoard.getSize();

        //Check whether last seed was placed in empty house
        if ((boardMap.get(finalPosition)) - 1 == 0) {
            int opposingPosition = gameBoard.getOpposingHouse(finalPosition, player);

            //Check if final position is in bounds of players houses and if opposing house is not empty
            if (opposingPosition != -1 && boardMap.get(opposingPosition) != 0) {
                int bonusSeeds = boardMap.put(opposingPosition, 0);
                //Add bonus seeds to current number of seeds in players base
                int currentSeeds = FIRST.equals(player) ? gameBoard.getFirstScore() : gameBoard.getSecondScore();
                boardMap.put(FIRST.equals(player) ? gameBoard.getFirstBase() : gameBoard.getSecondBase(), currentSeeds + bonusSeeds);

                return true;
            }
        }

        return false;
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

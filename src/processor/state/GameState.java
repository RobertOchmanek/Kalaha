package processor.state;

import board.GameBoard;
import interfaces.KalahaState;
import interfaces.KalahaState.GameResults;
import interfaces.KalahaState.GameStates;
import observers.state.KalahaStateBuilder;
import players.Player;
import processor.TurnProcessor;

import java.util.List;
import java.util.Map;

import static interfaces.KalahaState.GameResults.UNKNOWN;
import static interfaces.KalahaState.GameStates.AFTER_PLAYER1_TURN;
import static players.Player.FIRST;
import static players.Player.SECOND;
import static processor.state.StateName.INITIAL;

public abstract class GameState {

    protected final TurnProcessor turnContext;

    public GameState(TurnProcessor turnContext) {
        this.turnContext = turnContext;
    }

    public abstract void processTurn();

    public abstract StateName getStateName();

    protected void baseMove() {
        GameBoard gameBoard = turnContext.getGameBoard();

        int house = -1;
        while (!turnContext.validateMove(FIRST, house, gameBoard)) {
            house = turnContext.requestForMove(FIRST, gameBoard.getImmutableValues());
        }

        boolean additionalMove = makeMove(house, gameBoard, FIRST);

        //Game result is unknown at this point as it is determined in the end state
        turnContext.notifyObservers(generateState(gameBoard.getImmutableValues(), AFTER_PLAYER1_TURN, UNKNOWN));

        //Check whether the player has all houses empty, if so end the game
        if (!turnContext.canMove(FIRST)) {
            //EndState is responsible for calculating game result and notifying observers
            turnContext.changeState(new EndState(turnContext, SECOND));
        }
        //If player is eligible for additional move stay in current state (except for initial state), else transition to opponents state
        else if (!additionalMove) {
            turnContext.changeState(new SecondPlayerState(turnContext));
        } else if (INITIAL.equals(getStateName())) {
            turnContext.changeState(new FirstPlayerState(turnContext));
        }
    }

    protected boolean makeMove(int house, GameBoard gameBoard, Player player) {
        Map<Integer, Integer> boardMap = gameBoard.boardAsMap();

        int seeds = boardMap.put(house, 0);

        int move = 1;
        int seedsToPlace = seeds;
        int currentHouse = house;

        while (seedsToPlace > 0) {
            //Use modulo in case number of seeds is greater than board size
            currentHouse = (house + move) % gameBoard.getSize();

            //Place the seeds only in houses or players own base, skip opponents base
            if (!((FIRST.equals(player) && currentHouse == gameBoard.getSecondBase()) || (SECOND.equals(player) && currentHouse == gameBoard.getFirstBase()))) {
                int currentSeeds = boardMap.get(currentHouse);
                boardMap.put(currentHouse, currentSeeds + 1);
                --seedsToPlace;
            }
            ++move;
        }
        
        boolean gotBonusSeeds = getBonusSeeds(currentHouse, gameBoard, player);

        /*If player got bonus seeds the finishing position is in one of their houses thus is not eligible for additional move
          Else, check whether last seed was placed in a base and player is eligible for additional turn*/
        if (gotBonusSeeds) {
            return false;
        } else if (FIRST.equals(player)) {
            return (house + seeds) % gameBoard.getSize() == gameBoard.getFirstBase();
        } else {
            return (house + seeds) % gameBoard.getSize() == gameBoard.getSecondBase();
        }
    }

    //Check whether last seed was placed in one of players empty houses and if so, obtain bonus seeds from opposing house
    protected boolean getBonusSeeds(int finalPosition, GameBoard gameBoard, Player player) {
        Map<Integer, Integer> boardMap = gameBoard.boardAsMap();

        //Check whether last seed was placed in empty house
        if ((boardMap.get(finalPosition)) - 1 == 0) {
            int opposingPosition = gameBoard.getOpposingHouse(finalPosition, player);

            //Check if final position is in bounds of players houses and if opposing house is not empty
            if (opposingPosition != -1 && boardMap.get(opposingPosition) != 0) {
                int bonusSeeds = boardMap.put(opposingPosition, 0);
                //Seed placed in players own house should also be included in bonus seeds
                bonusSeeds += boardMap.put(finalPosition, 0);
                //Add bonus seeds to current number of seeds in players base
                int currentSeeds = FIRST.equals(player) ? gameBoard.getFirstScore() : gameBoard.getSecondScore();
                boardMap.put(FIRST.equals(player) ? gameBoard.getFirstBase() : gameBoard.getSecondBase(), currentSeeds + bonusSeeds);

                return true;
            }
        }

        return false;
    }

    protected KalahaState generateState(List<Integer> pitsState, GameStates gameState, GameResults gameResult) {
        return new KalahaStateBuilder()
                .setPitsState(pitsState)
                .setGameResult(gameResult)
                .setGameState(gameState)
                .build();
    }
}

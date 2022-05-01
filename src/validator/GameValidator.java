package validator;

import board.GameBoard;
import interfaces.KalahaState.GameResults;

import java.util.Map;

import static interfaces.KalahaState.GameResults.*;

//TODO: refactor into a chain of responsibilities?
public class GameValidator {

    //TODO: make this method universal for both players
    public boolean canMove(GameBoard gameBoard) {
        boolean canMove = false;
        Map<Integer, Integer> boardMap = gameBoard.boardAsMap();

        for (int house = 0; house < gameBoard.getNumHouses(); ++house) {
            if (boardMap.get(house) != 0) {
                canMove = true;
                break;
            }
        }
        return canMove;
    }

    public GameResults getGameResult(GameBoard gameBoard) {

        if (gameBoard.getFirstScore() == gameBoard.getSecondScore()) {
            return DRAW;
        } else if (gameBoard.getFirstScore() > gameBoard.getSecondScore()) {
            return PLAYER1_WON;
        } else {
            return PLAYER2_WON;
        }
    }
}

package validator;

import interfaces.KalahaState.GameResults;

import java.util.Map;

import static interfaces.KalahaState.GameResults.*;

//TODO: refactor into a chain of responsibilities
public class GameValidator {

    public boolean canMove(Map<Integer, Integer> board, int numHouses) {
        boolean canMove = false;
        for (int house = 0; house < numHouses; ++house) {
            if (board.get(house) != 0) {
                canMove = true;
                break;
            }
        }
        return canMove;
    }

    public GameResults getGameResult(Map<Integer, Integer> board) {

        if (board.firstScore == board.secondScore) {
            return DRAW;
        } else if (board.firstScore > board.secondScore) {
            return PLAYER1_WON;
        } else {
            return PLAYER2_WON;
        }
    }
}

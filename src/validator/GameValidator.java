package validator;

import board.GameBoard;
import interfaces.KalahaState.GameResults;
import players.Player;

import java.util.Map;

import static interfaces.KalahaState.GameResults.*;
import static players.Player.FIRST;

public class GameValidator {

    public boolean canMove(Player player, GameBoard gameBoard) {

        boolean canMove = false;
        Map<Integer, Integer> boardMap = gameBoard.boardAsMap();

        int firstHouse = FIRST.equals(player) ? 0 : gameBoard.getNumHouses() + 1;
        int base = FIRST.equals(player) ? gameBoard.getFirstBase() : gameBoard.getSecondBase();

        for (int house = firstHouse; house < base; ++house) {
            if (boardMap.get(house) != 0) {
                canMove = true;
                break;
            }
        }
        return canMove;
    }

    public boolean validateMove(Player player, int house, GameBoard gameBoard) {
        if (FIRST.equals(player)) {
            return house >= 0 && house < gameBoard.getFirstBase() && gameBoard.boardAsMap().get(house) != 0;
        } else {
            return house >= gameBoard.getFirstBase() + 1 && house < gameBoard.getSecondBase() && gameBoard.boardAsMap().get(house) != 0;
        }
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

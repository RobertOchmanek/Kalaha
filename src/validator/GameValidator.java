package validator;

import board.GameBoard;
import interfaces.KalahaState.GameResults;

import java.util.Map;

import static interfaces.KalahaState.GameResults.*;

//TODO: refactor into a chain of responsibilities
public class GameValidator implements Validator {

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

    @Override
    public GameResults getGameState(GameBoard board) {

        //Check if one of the players has all houses empty
        boolean player1OutOfStones = true;
        boolean player2OutOfStones = true;
        for (int i = 0; i < board.getNumberOfHouses(); i++) {
            if (board.getBoardAsMap().get(i) != 0) {
                player1OutOfStones = false;
                break;
            }
        }
        for (int i = board.getNumberOfHouses() + 1; i < board.getNumberOfHouses() * 2 + 1; i++) {
            if (board.getBoardAsMap().get(i) != 0) {
                player2OutOfStones = false;
                break;
            }
        }

        //If both players still have seeds in their houses result is unknown
        if (!player1OutOfStones && !player2OutOfStones) {
            return UNKNOWN;
        }

        //jak jest koniec to powiedz kto wygral (pamietaj o zsumowaniu pktow co ma u siebie 2 gracz)
        int player1Score = board.getPlayer1BaseScore() + (player1OutOfStones ? 0 : board.getAllRemainingPlayer1StonesCount());
        int player2Score = board.getPlayer2BaseScore() + (player2OutOfStones ? 0 : board.getAllRemainingPlayer2StonesCount());

        if (player1Score > player2Score) return PLAYER1_WON;
        if (player1Score < player2Score) return PLAYER2_WON;
        return DRAW;
    }
}

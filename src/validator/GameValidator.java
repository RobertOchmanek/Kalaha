package validator;

import board.Board;
import interfaces.KalahaState.GameResults;

import static interfaces.KalahaState.GameResults.*;

public class GameValidator implements Validator {

    @Override
    public GameResults getGameState(Board board) {
        //sprawdz czy w ogole robic koniec gry
        //koniec jest wtedy, kiedy przynajmniej jeden z graczy nie ma u siebie w dolkach nic
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

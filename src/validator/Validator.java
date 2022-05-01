package validator;

import board.GameBoard;
import interfaces.KalahaState;

public interface Validator {

    KalahaState.GameResults getGameState(GameBoard board);
}

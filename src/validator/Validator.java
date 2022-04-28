package validator;

import board.Board;
import interfaces.KalahaState;

public interface Validator {

    KalahaState.GameResults getGameState(Board board);
}

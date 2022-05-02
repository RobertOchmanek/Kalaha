package processor.state;

import board.GameBoard;
import interfaces.KalahaState;
import players.Player;
import processor.TurnProcessor;

public class InitialState extends GameState {

    public InitialState(TurnProcessor turnContext) {
        super(turnContext);
    }

    @Override
    public void processTurn() {
        turnContext.notifyObservers(generateState(turnContext.getGameBoard().getImmutableValues(), KalahaState.GameStates.INITAL_STATE, KalahaState.GameResults.UNKNOWN));
        baseMove();
    }

    protected boolean getBonusSeeds(int house, int seeds, GameBoard gameBoard, Player player) {
        return false;
    }
}

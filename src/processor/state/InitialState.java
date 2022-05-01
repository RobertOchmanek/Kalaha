package processor.state;

import board.GameBoard;
import players.Player;
import processor.TurnProcessor;

public class InitialState extends GameState {

    public InitialState(TurnProcessor turnContext) {
        super(turnContext);
    }

    @Override
    public void processTurn() {
        baseMove();
    }

    protected boolean getBonusSeeds(int house, int seeds, GameBoard gameBoard, Player player) {
        return false;
    }
}

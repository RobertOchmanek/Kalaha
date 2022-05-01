package processor.state;

import processor.TurnProcessor;

import static players.Player.*;

public class FirstPlayerState extends GameState {

    public FirstPlayerState(TurnProcessor turnContext) {
        super(turnContext);
    }

    @Override
    public void processTurn() {
        //Check whether the player can make the move at all (has at least one house with seeds > 0)
        if (turnContext.canMove(FIRST)) {
            baseMove();
        } else {
            //EndState is responsible for calculating game result and notifying observers
            turnContext.changeState(new EndState(turnContext, SECOND));
        }
    }
}

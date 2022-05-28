package processor.state;

import processor.TurnProcessor;

import static processor.state.StateName.FIRST_PLAYER;

public class FirstPlayerState extends GameState {

    public FirstPlayerState(TurnProcessor turnContext) {
        super(turnContext);
    }

    @Override
    public void processTurn() {
        baseMove();
    }

    @Override
    public StateName getStateName() {
        return FIRST_PLAYER;
    }
}

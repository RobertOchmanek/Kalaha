package processor.state;

import processor.TurnProcessor;

public class InitialState extends GameState {

    public InitialState(TurnProcessor turnContext) {
        super(turnContext);
    }

    @Override
    public void processTurn() {
        baseMove();
    }
}

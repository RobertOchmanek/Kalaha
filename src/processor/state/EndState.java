package processor.state;

import interfaces.KalahaState.GameStates;
import processor.TurnProcessor;

import static interfaces.KalahaState.GameStates.END_OF_GAME;

public class EndState extends GameState {

    public EndState(TurnProcessor turnContext) {
        super(turnContext);
    }

    @Override
    public void processTurn() {
        System.out.println("Game ended.");
    }

    @Override
    public GameStates getStateType() {
        return END_OF_GAME;
    }
}

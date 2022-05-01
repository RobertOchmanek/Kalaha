package processor.state;

import processor.TurnProcessor;

import java.util.Map;

public class EndState extends GameState {

    public EndState(TurnProcessor turnContext) {
        super(turnContext);
    }

    @Override
    public void processTurn() {
        //Calculate results
        //Notify observers
        //Unregister observers
    }

    @Override
    public boolean validateMove(int house, int houses, Map<Integer, Integer> board) {
        return false;
    }

    @Override
    public boolean makeMove(int house, Map<Integer, Integer> board) {
        return false;
    }
}

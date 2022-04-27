package processor.state;

import interfaces.KalahaState;
import processor.TurnProcessor;

import java.util.List;
import java.util.Map;

public class AfterSecondState extends GameState {

    public AfterSecondState(TurnProcessor turnContext) {
        super(turnContext);
    }

    @Override
    public void processTurn() {

    }

    @Override
    public boolean validateMove(int house, int houses, Map<Integer, Integer> board) {
        return false;
    }

    @Override
    public void makeMove(int house, Map<Integer, Integer> board) {

    }

    @Override
    public KalahaState generateState(List<Integer> pitsState) {
        return null;
    }
}

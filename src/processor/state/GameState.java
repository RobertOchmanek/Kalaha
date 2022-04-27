package processor.state;

import interfaces.KalahaState;
import interfaces.KalahaState.GameStates;
import processor.TurnProcessor;

import java.util.List;
import java.util.Map;

public abstract class GameState {

    protected final TurnProcessor turnContext;

    public GameState(TurnProcessor turnContext) {
        this.turnContext = turnContext;
    }

    public abstract void processTurn();

    public abstract boolean validateMove(int house, int houses, Map<Integer, Integer> board);

    public abstract void makeMove(int house, Map<Integer, Integer> board);

    public abstract KalahaState generateState(List<Integer> pitsState);
}

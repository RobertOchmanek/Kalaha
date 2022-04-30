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

    public abstract boolean makeMove(int house, Map<Integer, Integer> board);

    public KalahaState generateState(List<Integer> pitsState, GameStates gameState, KalahaState.GameResults gameResult) {
        return new KalahaState() {

            @Override
            public List<Integer> getPitsState() {
                return pitsState;
            }

            @Override
            public GameStates getGameState() {
                return gameState;
            }

            @Override
            public GameResults getGameResult() {
                return gameResult;
            }
        };
    }
}

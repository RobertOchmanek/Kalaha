package observers;

import java.util.List;

public class KalahaState implements interfaces.KalahaState {

    private final List<Integer> pitsState;
    private final GameStates gameState;
    private final GameResults gameResult;

    public KalahaState(KalahaStateBuilder builder) {
        this.pitsState = builder.getPitsState();
        this.gameResult = builder.getGameResult();
        this.gameState = builder.getGameState();
    }

    @Override
    public List<Integer> getPitsState() {
        return this.pitsState;
    }

    @Override
    public GameStates getGameState() {
        return this.gameState;
    }

    @Override
    public GameResults getGameResult() {
        return this.gameResult;
    }
}

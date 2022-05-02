package observers.state;

import interfaces.KalahaState;

import java.util.List;

public class KalahaStateBuilder {

    private List<Integer> pitsState;
    private KalahaState.GameStates gameState;
    private KalahaState.GameResults gameResult;

    public KalahaStateBuilder setPitsState(List<Integer> pitsState) {
        this.pitsState = pitsState;
        return this;
    }

    public KalahaStateBuilder setGameState(KalahaState.GameStates gameState) {
        this.gameState = gameState;
        return this;
    }

    public KalahaStateBuilder setGameResult(KalahaState.GameResults gameResult) {
        this.gameResult = gameResult;
        return this;
    }

    public KalahaState build() {
        return new observers.state.KalahaState(this);
    }

    public List<Integer> getPitsState() {
        return pitsState;
    }

    public KalahaState.GameStates getGameState() {
        return gameState;
    }

    public KalahaState.GameResults getGameResult() {
        return gameResult;
    }
}

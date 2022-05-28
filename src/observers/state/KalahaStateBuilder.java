package observers.state;

import interfaces.KalahaState.GameStates;
import interfaces.KalahaState.GameResults;

import java.util.List;

public class KalahaStateBuilder {

    private List<Integer> pitsState;
    private GameStates gameState;
    private GameResults gameResult;

    public KalahaStateBuilder setPitsState(List<Integer> pitsState) {
        this.pitsState = pitsState;
        return this;
    }

    public KalahaStateBuilder setGameState(GameStates gameState) {
        this.gameState = gameState;
        return this;
    }

    public KalahaStateBuilder setGameResult(GameResults gameResult) {
        this.gameResult = gameResult;
        return this;
    }

    public KalahaState build() {
        return new KalahaState(this);
    }

    public List<Integer> getPitsState() {
        return pitsState;
    }

    public GameStates getGameState() {
        return gameState;
    }

    public GameResults getGameResult() {
        return gameResult;
    }
}

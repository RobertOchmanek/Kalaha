package tests;

import interfaces.GameStateObserver;
import interfaces.KalahaState;

public class Observer implements GameStateObserver {
    @Override
    public void currentState(KalahaState state) {
        System.out.println("Pits state to: " + state.getPitsState());
        System.out.println("Game state to: " + state.getGameState().name());
        System.out.println("Game result to: " + state.getGameResult());
    }
}

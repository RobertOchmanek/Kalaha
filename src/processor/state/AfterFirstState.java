package processor.state;

import interfaces.KalahaState.GameStates;
import processor.TurnProcessor;

import java.util.LinkedHashMap;
import java.util.Map;

import static interfaces.KalahaState.GameStates.AFTER_PLAYER1_TURN;

public class AfterFirstState extends GameState {

    public AfterFirstState(TurnProcessor turnContext) {
        super(turnContext);
    }

    @Override
    public void processTurn() {

    }

    @Override
    public GameStates getStateType() {
        return AFTER_PLAYER1_TURN;
    }

    public Map<Integer, Integer> getReversedBoardView() {
        Map<Integer, Integer> firstPlayerViewBoard = turnContext.getBoard();
        Map<Integer, Integer> secondPlayerViewBoard = new LinkedHashMap<>();
        int numHouses = turnContext.getNumHouses();
        //create second player side
        for (int i = 0; i < numHouses; i++) {
            secondPlayerViewBoard.put(i, firstPlayerViewBoard.get(i + numHouses + 1));
        }
        //create second player base
        secondPlayerViewBoard.put(numHouses, firstPlayerViewBoard.get(numHouses * 2 + 1));
        //create first player side
        for (int i = 0; i < numHouses; i++) {
            secondPlayerViewBoard.put(numHouses + 1, firstPlayerViewBoard.get(i));
        }
        //create first player base
        secondPlayerViewBoard.put(numHouses * 2 + 1, firstPlayerViewBoard.get(numHouses));
        return secondPlayerViewBoard;
    }
}

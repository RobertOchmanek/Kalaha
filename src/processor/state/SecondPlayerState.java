package processor.state;

import board.GameBoard;
import processor.TurnProcessor;

public class SecondPlayerState extends GameState {

    public SecondPlayerState(TurnProcessor turnContext) {
        super(turnContext);
    }

    @Override
    public void processTurn() {

    }

    @Override
    public boolean makeMove(int house, GameBoard gameBoard) {
        return false;
    }

    /*public Map<Integer, Integer> getReversedBoardView() {
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
    }*/
}

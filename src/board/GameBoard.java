package board;

import java.util.LinkedHashMap;
import java.util.Map;

public class GameBoard implements Board {

    private Map<Integer, Integer> boardMap;
    private final int houseNumber;

    public GameBoard(int initialSeedsCount, int houseNumber) {
        this.houseNumber = houseNumber;
        this.boardMap = new LinkedHashMap<>();
        int totalFields = (2 * houseNumber) + 2;
        for (int field = 0; field < totalFields; ++field) {
            if (field == houseNumber || field == (2 * houseNumber) + 1) {
                boardMap.put(field, 0);
            } else {
                boardMap.put(field, initialSeedsCount);
            }
        }
    }

    @Override
    public int getPlayer1BaseScore() {
        return boardMap.get(houseNumber);
    }

    @Override
    public int getPlayer2BaseScore() {
        return boardMap.get(houseNumber * 2 + 1);
    }

    public int getAllRemainingPlayer1StonesCount() {
        int stonesCount = 0;
        for (int i = 0; i < houseNumber; i++) {
            stonesCount += boardMap.get(i);
        }
        return stonesCount;
    }

    public int getAllRemainingPlayer2StonesCount() {
        int stonesCount = 0;
        for (int i = houseNumber + 1; i < houseNumber * 2 + 1; i++) {
            stonesCount += boardMap.get(i);
        }
        return stonesCount;
    }

    @Override
    public int getNumberOfHouses() {
        return houseNumber;
    }

    @Override
    public Map<Integer, Integer> getBoardAsMap() {
        return boardMap;
    }

    @Override
    public void setNewBoardState(Map<Integer, Integer> newBoard) {
        if (newBoard.keySet().size() != boardMap.keySet().size()) {
            System.out.println("Wrong size, couldn't change the board");
            return;
        }
        boardMap = newBoard;
    }
}

package board;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public class GameBoard {

    private final Map<Integer, Integer> boardMap;
    private final int size;
    private final int numHouses;
    private final int firstBase;
    private final int secondBase;

    public GameBoard(int houses, int seeds) {
        //TODO: use map that returns values in the same order as they were added
        this.boardMap = new LinkedHashMap<>();
        this.size = (2 * houses) + 2;
        this.numHouses = houses;
        this.firstBase = houses;
        this.secondBase = (2 * houses) + 1;

        for (int field = 0; field <= secondBase; ++field) {
            if (field == firstBase || field == secondBase) {
                boardMap.put(field, 0);
            } else {
                boardMap.put(field, seeds);
            }
        }
    }

    public Map<Integer, Integer> boardAsMap() {
        return boardMap;
    }

    public List<Integer> getImmutableValues() {
        return List.copyOf(boardMap.values());
    }

    //TODO: make this method universal for both players
    public int getOpposingHouse(int house) {
        if (house == firstBase) {
            return secondBase;
        } else if (house == secondBase) {
            return firstBase;
        } else {
            return house + (2 * (numHouses - house));
        }
    }

    //TODO: make this method universal for both players
    public boolean isInBounds(int house) {
        return house >= 0 && house < numHouses;
    }

    public int getSize() {
        return size;
    }

    public int getNumHouses() {
        return numHouses;
    }

    public int getFirstBase() {
        return firstBase;
    }

    public int getSecondBase() {
        return secondBase;
    }

    public int getFirstScore() {
        return boardMap.get(firstBase);
    }

    public int getSecondScore() {
        return boardMap.get(secondBase);
    }
}

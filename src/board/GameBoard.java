package board;

import players.Player;

import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import static players.Player.FIRST;

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

    public List<Integer> getReversedImmutableValues() {
        List<Integer> reversedValues = new LinkedList<>();

        for (int house = firstBase + 1; house <= secondBase; ++house) {
            reversedValues.add(boardMap.get(house));
        }

        for (int house = 0; house <= firstBase; ++house) {
            reversedValues.add(boardMap.get(house));
        }

        return List.copyOf(reversedValues);
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

    public boolean isInBounds(Player player, int house) {
        if (FIRST.equals(player)) {
            return house >= 0 && house < firstBase;
        } else {
            return house >= firstBase + 1 && house < secondBase;
        }
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

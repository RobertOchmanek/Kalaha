package board;

import java.util.Map;

public interface Board {

    int getPlayer1BaseScore();

    int getPlayer2BaseScore();

    int getNumberOfHouses();

    Map<Integer, Integer> getBoardAsMap();

    void setNewBoardState(Map<Integer, Integer> board);

    int getAllRemainingPlayer1StonesCount();

    int getAllRemainingPlayer2StonesCount();
}

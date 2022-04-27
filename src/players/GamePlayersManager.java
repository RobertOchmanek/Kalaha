package players;

import interfaces.KalahPlayer;

import java.util.List;
import java.util.Objects;

public class GamePlayersManager implements PlayersManager {

    private KalahPlayer firstPlayer = null;

    private KalahPlayer secondPlayer = null;

    public GamePlayersManager() {
    }

    @Override
    public void registerPlayer(KalahPlayer kalahPlayer) {
        if (Objects.isNull(firstPlayer)) {
            firstPlayer = kalahPlayer;
        } else {
            secondPlayer = kalahPlayer; //if more invokes than 2, set new second player
        }
    }

    @Override
    public int requestForMove(Player player, List<Integer> pitsState) {
        if (player.equals(Player.FIRST)) {
            return firstPlayer.yourMove(pitsState);
        }
        return secondPlayer.yourMove(pitsState);
    }
}

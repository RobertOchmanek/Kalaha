package players;

import interfaces.KalahPlayer;

import java.util.List;
import java.util.Objects;

import static players.Player.FIRST;

public class PlayersManager {

    private KalahPlayer firstPlayer = null;
    private KalahPlayer secondPlayer = null;

    public PlayersManager() {
    }

    public void registerPlayer(KalahPlayer kalahPlayer) {
        if (Objects.isNull(firstPlayer)) {
            firstPlayer = kalahPlayer;
        } else {
            secondPlayer = kalahPlayer; //if more invokes than 2, set new second player
        }
    }

    public int requestForMove(Player player, List<Integer> pitsState) {
        if (player.equals(FIRST)) {
            return firstPlayer.yourMove(pitsState);
        }
        return secondPlayer.yourMove(pitsState);
    }
}

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
            secondPlayer = kalahPlayer;
        }
    }

    public int requestForMove(Player player, List<Integer> pitsState) {
        if (FIRST.equals(player)) {
            return firstPlayer.yourMove(pitsState);
        }
        return secondPlayer.yourMove(pitsState);
    }
}

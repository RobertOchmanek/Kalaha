package players;

import interfaces.KalahPlayer;

import java.util.List;

public interface PlayersManager {

    void registerPlayer(KalahPlayer kalahPlayer);

    int requestForMove(Player player, List<Integer> pitsState);
}

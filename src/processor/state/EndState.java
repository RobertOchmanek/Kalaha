package processor.state;

import interfaces.KalahaState.GameResults;
import players.Player;
import processor.TurnProcessor;

import java.util.List;
import java.util.Map;

import static interfaces.KalahaState.GameStates.END_OF_GAME;
import static players.Player.FIRST;

public class EndState extends GameState {

    private final Player finishingPlayer;

    public EndState(TurnProcessor turnContext, Player finishingPlayer) {
        super(turnContext);
        this.finishingPlayer = finishingPlayer;
    }

    @Override
    public void processTurn() {
        makeMove(0, turnContext.getBoard());
        GameResults gameResult = turnContext.getGameResult();
        turnContext.getObserversManager().notifyObservers(generateState(List.copyOf(turnContext.getBoard().values()), END_OF_GAME, gameResult));
        //TODO: do we need to unregister observers to be compliant with the pattern (even simple .remove())?
    }

    @Override
    public boolean validateMove(int house, int houses, Map<Integer, Integer> board) {
        return false;
    }

    @Override
    public boolean makeMove(int house, Map<Integer, Integer> board) {
        //Get indexes of base and houses to check for stones
        int base = FIRST.equals(finishingPlayer) ? turnContext.getNumHouses() : (2 * turnContext.getNumHouses()) + 1;
        int startingHouse = FIRST.equals(finishingPlayer) ? 0 : turnContext.getNumHouses() + 1;
        int endingHouse = FIRST.equals(finishingPlayer) ? turnContext.getNumHouses() : (2 * turnContext.getNumHouses()) + 1;

        int leftoverSeeds = 0;

        //Accumulate sum of leftover seeds for loosing player
        for (int currentHouse = startingHouse; currentHouse < endingHouse; ++currentHouse) {
            leftoverSeeds += board.put(currentHouse, 0);
        }

        //Sum leftover seeds with players score and insert into base
        leftoverSeeds += board.get(base);
        board.put(base, leftoverSeeds);

        //Collecting leftover stones is the last move thus no additional move can be produced
        return false;
    }
}

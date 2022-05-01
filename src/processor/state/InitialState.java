package processor.state;

import processor.TurnProcessor;

import java.util.List;
import java.util.Map;

import static interfaces.KalahaState.GameResults.UNKNOWN;
import static interfaces.KalahaState.GameStates.AFTER_PLAYER1_TURN;
import static players.Player.FIRST;

public class InitialState extends GameState {

    public InitialState(TurnProcessor turnContext) {
        super(turnContext);
    }

    @Override
    public void processTurn() {
        int house = -1;
        while (!validateMove(house, turnContext.getNumHouses(), turnContext.getBoard())) {
            house = turnContext.getPlayersManager().requestForMove(FIRST, List.copyOf(turnContext.getBoard().values()));
        }

        boolean additionalMove = makeMove(house, turnContext.getBoard());

        //After initial turn game result is always unknown
        turnContext.getObserversManager().notifyObservers(generateState(List.copyOf(turnContext.getBoard().values()), AFTER_PLAYER1_TURN, UNKNOWN));

        //If player is eligible for additional move skip transition to opponents state
        if (additionalMove) {
            turnContext.changeState(new FirstPlayerState(turnContext));
        } else {
            turnContext.changeState(new SecondPlayerState(turnContext));
        }
    }

    @Override
    public boolean validateMove(int house, int houses, Map<Integer, Integer> board) {
        return house >= 0 && house < houses && board.get(house) != 0;
    }

    @Override
    public boolean makeMove(int house, Map<Integer, Integer> board) {
        int seeds = board.get(house);
        board.put(house, 0);

        for (int move = 1; move <= seeds; ++move) {
            int currentSeeds = board.get(house + move);
            board.put(house + move, currentSeeds + 1);
        }

        //TODO: replace additional move check with validator step?
        //Check whether last seed was placed in a base and player is eligible for additional turn
        return house + seeds == turnContext.getNumHouses();
    }
}

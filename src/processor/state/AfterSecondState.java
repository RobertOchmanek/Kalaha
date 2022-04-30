package processor.state;

import processor.TurnProcessor;

import java.util.List;
import java.util.Map;

import static interfaces.KalahaState.GameResults.UNKNOWN;
import static interfaces.KalahaState.GameStates.AFTER_PLAYER1_TURN;
import static players.Player.FIRST;

public class AfterSecondState extends GameState {

    public AfterSecondState(TurnProcessor turnContext) {
        super(turnContext);
    }

    @Override
    public void processTurn() {
        //Check whether the player can make the move at all (has at least one house with seeds > 0)
        if (turnContext.canMove()) {
            int house = -1;
            while (!validateMove(house, turnContext.getNumHouses(), turnContext.getBoard())) {
                house = turnContext.getPlayersManager().requestForMove(FIRST, List.copyOf(turnContext.getBoard().values()));
            }

            boolean additionalMove = makeMove(house, turnContext.getBoard());

            //Game result is unknown at this point as it is determined at the beginning of players move
            turnContext.getObserversManager().notifyObservers(generateState(List.copyOf(turnContext.getBoard().values()), AFTER_PLAYER1_TURN, UNKNOWN));

            //If player is eligible for additional move skip transition to opponents state
            if (additionalMove) {
                turnContext.changeState(new AfterSecondState(turnContext));
            } else {
                turnContext.changeState(new AfterFirstState(turnContext));
            }
        } else {
            //EndState is responsible for calculating game result and notifying observers
            turnContext.changeState(new EndState(turnContext));
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

        //TODO: replace bonus seeds check with validator step?
        //Check whether last seed was placed in one of players houses and if so, obtain bonus seeds from opposing house
        int finalPosition = (house + seeds) % board.size();
        //Calculate opposing houses index based on final position and number of all houses
        int opposingPosition = finalPosition + (2 * (turnContext.getNumHouses() - finalPosition));
        //Check if final position is in bounds of players houses and if opposing house is not empty
        if (finalPosition >= 0 && finalPosition < turnContext.getNumHouses() && board.get(opposingPosition) != 0) {
            int bonusSeeds = board.put(opposingPosition, 0);
            //Add bonus seeds to current number of seeds in players base
            int currentSeeds = board.get(turnContext.getNumHouses());
            board.put(turnContext.getNumHouses(), currentSeeds + bonusSeeds);
            //Player finished in one of their houses
            return false;
        }

        //TODO: replace additional move check with validator step?
        //Check whether last seed was placed in a base and player is eligible for additional turn
        return house + seeds == turnContext.getNumHouses();
    }
}

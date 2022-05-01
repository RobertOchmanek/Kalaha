package processor.state;

import board.GameBoard;
import processor.TurnProcessor;

import java.util.Map;

import static players.Player.*;

public class FirstPlayerState extends GameState {

    public FirstPlayerState(TurnProcessor turnContext) {
        super(turnContext);
    }

    @Override
    public void processTurn() {
        //Check whether the player can make the move at all (has at least one house with seeds > 0)
        if (turnContext.canMove(FIRST)) {
            baseMove();
        } else {
            //EndState is responsible for calculating game result and notifying observers
            turnContext.changeState(new EndState(turnContext, SECOND));
        }
    }

    @Override
    protected boolean getBonusSeeds(int house, int seeds, GameBoard gameBoard) {
        Map<Integer, Integer> boardMap = gameBoard.boardAsMap();

        //Check whether last seed was placed in one of players houses and if so, obtain bonus seeds from opposing house
        int finalPosition = (house + seeds) % gameBoard.getSize();
        int opposingPosition = gameBoard.getOpposingHouse(finalPosition);

        //Check if final position is in bounds of players houses and if opposing house is not empty
        if (gameBoard.isInBounds(FIRST, finalPosition) && boardMap.get(opposingPosition) != 0) {
            int bonusSeeds = boardMap.put(opposingPosition, 0);
            //Add bonus seeds to current number of seeds in players base
            int currentSeeds = gameBoard.getFirstScore();
            boardMap.put(gameBoard.getFirstBase(), currentSeeds + bonusSeeds);

            return true;
        }

        return false;
    }
}

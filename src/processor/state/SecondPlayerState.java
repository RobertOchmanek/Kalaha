package processor.state;

import board.GameBoard;
import processor.TurnProcessor;

import static interfaces.KalahaState.GameResults.UNKNOWN;
import static interfaces.KalahaState.GameStates.AFTER_PLAYER2_TURN;
import static players.Player.*;

public class SecondPlayerState extends GameState {

    public SecondPlayerState(TurnProcessor turnContext) {
        super(turnContext);
    }

    @Override
    public void processTurn() {
        //Check whether the player can make the move at all (has at least one house with seeds > 0)
        if (turnContext.canMove(SECOND)) {

            GameBoard gameBoard = turnContext.getGameBoard();

            int house = -1;
            while (!turnContext.validateMove(SECOND, house, gameBoard)) {
                house = turnContext.requestForMove(SECOND, gameBoard.getReversedImmutableValues());
                //Convert chosen house to first players point of view
                house = house + gameBoard.getFirstBase() + 1;
            }

            boolean additionalMove = makeMove(house, gameBoard);

            //Game result is unknown at this point as it is determined at the beginning of players move
            turnContext.notifyObservers(generateState(gameBoard.getImmutableValues(), AFTER_PLAYER2_TURN, UNKNOWN));

            //If player is eligible for additional stay in current state, else transition to opponents state
            if (!additionalMove) {
                turnContext.changeState(new FirstPlayerState(turnContext));
            }

        } else {
            //EndState is responsible for calculating game result and notifying observers
            turnContext.changeState(new EndState(turnContext, FIRST));
        }
    }

    @Override
    public boolean makeMove(int house, GameBoard gameBoard) {
        //TODO: second player move
        return false;
    }
}

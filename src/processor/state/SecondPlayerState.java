package processor.state;

import board.GameBoard;
import processor.TurnProcessor;

import static interfaces.KalahaState.GameResults.UNKNOWN;
import static interfaces.KalahaState.GameStates.AFTER_PLAYER2_TURN;
import static players.Player.*;
import static processor.state.StateName.SECOND_PLAYER;

public class SecondPlayerState extends GameState {

    public SecondPlayerState(TurnProcessor turnContext) {
        super(turnContext);
    }

    @Override
    public void processTurn() {

        GameBoard gameBoard = turnContext.getGameBoard();

        int house = -1;
        while (!turnContext.validateMove(SECOND, house, gameBoard)) {
            house = turnContext.requestForMove(SECOND, gameBoard.getReversedImmutableValues());
            //Convert chosen house to first players point of view
            house = house + gameBoard.getFirstBase() + 1;
        }

        boolean additionalMove = makeMove(house, gameBoard, SECOND);

        //Game result is unknown at this point as it is determined in the end state
        turnContext.notifyObservers(generateState(gameBoard.getImmutableValues(), AFTER_PLAYER2_TURN, UNKNOWN));

        //Check whether the player has all houses empty, if so end the game
        if (!turnContext.canMove(SECOND)) {
            //EndState is responsible for calculating game result and notifying observers
            turnContext.changeState(new EndState(turnContext, FIRST));
        }
        //If player is eligible for additional stay move in current state, else transition to opponents state
        if (!additionalMove) {
            turnContext.changeState(new FirstPlayerState(turnContext));
        }
    }

    @Override
    public StateName getStateName() {
        return SECOND_PLAYER;
    }
}

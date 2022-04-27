package processor.state;

import interfaces.KalahaState.GameStates;
import players.Player;
import processor.TurnProcessor;

import java.util.List;

import static interfaces.KalahaState.GameStates.INITAL_STATE;
import static players.Player.FIRST;

public class InitialState extends GameState {

    public InitialState(TurnProcessor turnContext) {
        super(turnContext);
    }

    @Override
    public void processTurn() {
        //Wykonaj ruch jako gracz 1
        boolean correctTurn = false;
        int house;
        while (!correctTurn) {
            house = turnContext.getPlayersManager().makeMove(FIRST, List.copyOf(turnContext.getBoard().values()));
            if (house >= 0 && house < turnContext.getNumHouses() && turnContext.getBoard().get(house) != 0) {
                correctTurn = true;
            }
        }
    }

    @Override
    public GameStates getStateType() {
        return INITAL_STATE;
    }
}

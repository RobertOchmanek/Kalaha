package processor.state;

import interfaces.KalahaState;
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
            house = turnContext.getPlayersManager().makeMove(FIRST, List.copyOf(turnContext.getBoard().values()));
        }
        makeMove(house, turnContext.getBoard());
        turnContext.getObserversManager().notifyObservers(generateState(List.copyOf(turnContext.getBoard().values())));
        turnContext.changeState(new AfterFirstState(turnContext));
    }

    @Override
    public boolean validateMove(int house, int houses, Map<Integer, Integer> board) {
        return house >= 0 && house < houses && board.get(house) != 0;
    }

    @Override
    public void makeMove(int house, Map<Integer, Integer> board) {
        int seeds = board.get(house);
        board.put(house, 0);
        for (int move = 1; move <= seeds; ++move) {
            int currentSeeds = board.get(house + move);
            board.put(house + move, currentSeeds + 1);
        }
    }

    @Override
    public KalahaState generateState(List<Integer> pitsState) {
        return new KalahaState() {

            @Override
            public List<Integer> getPitsState() {
                return pitsState;
            }

            @Override
            public GameStates getGameState() {
                return AFTER_PLAYER1_TURN;
            }

            @Override
            public GameResults getGameResult() {
                return UNKNOWN;
            }
        };
    }
}

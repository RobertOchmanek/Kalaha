package tests;

import interfaces.KalahPlayer;

import java.util.List;
import java.util.Scanner;

public class KalahaPlayer implements KalahPlayer {

    private final String playerId;

    public KalahaPlayer(String playerId) {
        this.playerId = playerId;
    }

    @Override
    public int yourMove(List<Integer> pitsState) {
        System.out.println("Jako gracz " + playerId + " prosze o ruch");
        Scanner scanner = new Scanner(System.in);
        String move = scanner.nextLine();
        return Integer.parseInt(move);
    }
}

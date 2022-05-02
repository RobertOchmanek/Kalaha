import tests.KalahaPlayer;
import tests.Observer;

public class TestRunner {

    public static void main(String[] args) {
        KalahaGame game = new KalahaGame();
        game.setVariant(4, 3);
        game.registerPlayer(new KalahaPlayer("1"));
        game.registerPlayer(new KalahaPlayer("2"));
        game.addObserver(new Observer());
        game.startGame();
    }
}

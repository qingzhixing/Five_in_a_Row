package starter;

import core.ChessPiece;
import core.RandomAIPlayer;
import swing.SwingGameController;

public class SwingStarter {
    public static void main(String[] args) {
        System.out.println("SwingStarter");
        SwingGameController gameController = new SwingGameController(
                15,
                new RandomAIPlayer(ChessPiece.BLACK),
                new RandomAIPlayer(ChessPiece.WHITE)
        );
        gameController.Start();
    }
}
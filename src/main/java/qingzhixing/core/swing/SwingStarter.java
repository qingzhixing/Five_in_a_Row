package qingzhixing.core.swing;

import qingzhixing.core.console.ChessPiece;

public class SwingStarter {
    public static void main(String[] args) {
        System.out.println("SwingStarter");
        SwingGameController gameController = new SwingGameController(
                15,
                new UIHumanPlayer(ChessPiece.BLACK),
                new UIHumanPlayer(ChessPiece.WHITE)
        );
        gameController.Start();
    }
}
package qingzhixing.core.swing;

import org.apache.log4j.Logger;
import qingzhixing.core.console.ChessPiece;

public class SwingStarter {
    private static final Logger logger = Logger.getLogger(SwingStarter.class);

    public static void main(String[] args) {
        logger.info("SwingStarter");
        SwingGameController gameController = new SwingGameController(
                15,
                new UIHumanPlayer(ChessPiece.BLACK),
                new UIHumanPlayer(ChessPiece.WHITE)
        );
        gameController.Start();
    }
}
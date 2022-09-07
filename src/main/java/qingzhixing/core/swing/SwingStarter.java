package qingzhixing.core.swing;

import org.apache.log4j.Logger;
import qingzhixing.core.console.ChessPiece;
import qingzhixing.core.console.GreedyAIPlayer;

public class SwingStarter {
    private static final Logger logger = Logger.getLogger(SwingStarter.class);

    public static void main(String[] args) {
        logger.info("SwingStarter");
        SwingGameController gameController = new SwingGameController(
                15,
                new GreedyAIPlayer(ChessPiece.BLACK),
                new GreedyAIPlayer(ChessPiece.WHITE)
        );
        gameController.Start();
    }
}
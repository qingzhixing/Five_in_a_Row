package qingzhixing.core.swing;

import org.apache.log4j.Logger;
import org.jetbrains.annotations.NotNull;
import qingzhixing.core.console.*;

import javax.swing.*;

public class SwingGameController extends AbstractGameController {
    private static final Logger logger = Logger.getLogger(SwingGameController.class);
    static int gridSideLength = 30;

    static int padding = 10;

    private SwingController SwingController;

    public SwingGameController(int boardSize, AbstractPlayer blackPlayer, AbstractPlayer whitePlayer) {
        super(boardSize, blackPlayer, whitePlayer);
    }

    public SwingGameController() {
        super();
    }

    public SwingGameController(ChessBoard board) {
        super(board);
    }

    public SwingGameController(int boardSize) {
        super(boardSize);
    }

    @Override
    protected void OnGameStart() {
        SwingController = new SwingController(gridSideLength, padding, GetChessBoard());
        SwingController.frame.setVisible(true);
        if (blackPlayer instanceof UIHumanPlayer) {
            ((UIHumanPlayer) blackPlayer).BindMouseClickedList(SwingController.component.mouseClicked);
        }
        if (whitePlayer instanceof UIHumanPlayer) {
            ((UIHumanPlayer) whitePlayer).BindMouseClickedList(SwingController.component.mouseClicked);
        }
    }

    @Override
    protected void DisplayBoard() {
        SwingController.frame.repaint();
    }

    @Override
    protected void DisplayWinner(@NotNull ChessPiece winner) {
        if (winner == ChessPiece.EMPTY) {
            JOptionPane.showMessageDialog(null, "There is no winner", "No winner", JOptionPane.INFORMATION_MESSAGE);
        } else {
            JOptionPane.showMessageDialog(null, winner.name() + " is the winner", "Winner", JOptionPane.INFORMATION_MESSAGE);
        }
    }

    @Override
    protected boolean IsAbleToRestart() {
        int option = JOptionPane.showConfirmDialog(null, "Do you want to restart?", "Restart", JOptionPane.YES_NO_OPTION);
        return option == JOptionPane.YES_OPTION;
    }


    @Override
    protected void OnMove(@NotNull ChessPiece player, @NotNull Coordinate lastMove) {
//        JOptionPane.showMessageDialog(null, "Player " + player.name() + " moved to " + lastMove, "Debug Message", JOptionPane.INFORMATION_MESSAGE);
        logger.debug("Player " + player.name() + " moved to " + lastMove);
    }


}

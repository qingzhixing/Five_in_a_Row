package swing;

import core.AbstractGameController;
import core.AbstractPlayer;
import core.ChessBoard;
import core.ChessPiece;
import org.jetbrains.annotations.NotNull;

import javax.swing.*;

public class SwingGameController extends AbstractGameController {
    static int gridSideLength=80;

    static int padding=10;

    private SwingController SwingController;
    public SwingGameController(int boardSize, AbstractPlayer blackPlayer, AbstractPlayer whitePlayer){
        super(boardSize,blackPlayer,whitePlayer);
    }
    public SwingGameController(){
        super();
    }
    public SwingGameController(ChessBoard board){
        super(board);
    }
    public SwingGameController(int boardSize){
        super(boardSize);
    }

    @Override
    public void OnGameStart() {
        SwingController = new SwingController(gridSideLength,padding,GetChessBoard());
        SwingController.frame.setVisible(true);
    }

    @Override
    protected void DisplayBoard() {
        SwingController.frame.repaint();
    }

    @Override
    protected void DisplayWinner(@NotNull ChessPiece winner) {
        if(winner==)
    }

    @Override
    protected boolean IsAbleToRestart() {
        return false;
    }
}

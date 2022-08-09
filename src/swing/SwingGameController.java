package swing;

import core.AbstractGameController;
import core.AbstractPlayer;
import core.ChessBoard;
import core.ChessPiece;
import org.jetbrains.annotations.NotNull;

import javax.swing.*;

public class SwingGameController extends AbstractGameController {
    static int gridSideLength=10;

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
    protected void OnGameStart() {
        SwingController = new SwingController(gridSideLength,padding,GetChessBoard());
        SwingController.frame.setVisible(true);
    }

    @Override
    protected void DisplayBoard() {
        SwingController.frame.repaint();
    }

    private void ShowGameOverDialog(@NotNull String message){
        JOptionPane.showMessageDialog(null,message,"Game Over",JOptionPane.INFORMATION_MESSAGE);
    }

    @Override
    protected void DisplayWinner(@NotNull ChessPiece winner) {
        if(winner==ChessPiece.EMPTY){
            ShowGameOverDialog("There is no winner!");
        }else{
            ShowGameOverDialog("Congratulations! "+winner.name()+" is the winner!");
        }
    }

    @Override
    protected boolean IsAbleToRestart() {
        return false;
    }


}

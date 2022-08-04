package swing;

import core.AbstractGameController;
import core.AbstractPlayer;
import core.ChessBoard;
import core.ChessPiece;

public class SwingGameController extends AbstractGameController {
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
    protected void DisplayBoard() {

    }

    @Override
    protected void DisplayWinner(ChessPiece winner) {

    }

    @Override
    protected boolean IsAbleToRestart() {
        return false;
    }
}

package core;

public class ConsoleGameController extends AbstractGameController{
    public ConsoleGameController(int boardSize,AbstractPlayer blackPlayer,AbstractPlayer whitePlayer){
        super(boardSize,blackPlayer,whitePlayer);
    }
    public ConsoleGameController(){
        super();
    }
    public ConsoleGameController(ChessBoard board){
        super(board);
    }

    @Override
    protected void DisplayBoard() {
        chessBoard.DebugPrintOnConsole();
    }

    @Override
    protected void DisplayWinner(ChessPiece winner) {

    }

    @Override
    protected boolean IsAbleToRestart() {
        return false;
    }
}

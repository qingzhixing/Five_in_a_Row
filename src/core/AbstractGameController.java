package core;

import org.jetbrains.annotations.NotNull;

public abstract class AbstractGameController {
    protected GameControllerListener listener= new GameControllerListener();
    protected final ChessBoard chessBoard;
    protected AbstractPlayer blackPlayer;
    protected AbstractPlayer whitePlayer;

    protected AbstractGameController(int boardSize,AbstractPlayer blackPlayer,AbstractPlayer whitePlayer){
        chessBoard = new ChessBoard(boardSize);
        this.blackPlayer = blackPlayer;
        this.whitePlayer = whitePlayer;
    }

    protected AbstractGameController(){
        this(15,new FoolAIPlayer(ChessPiece.BLACK),new FoolAIPlayer(ChessPiece.WHITE));
    }

    protected AbstractGameController(ChessBoard board){
        this.chessBoard = board;
    }

    public final ChessBoard GetChessBoard(){
        return chessBoard;
    }

    public void Start(){
        do{
            listener.OnGameStart();

            ChessBoard.Coordinate lastMove = null;
            ChessPiece winner = ChessPiece.EMPTY;
            while (winner == ChessPiece.EMPTY) {

                lastMove = blackPlayer.MoveIn(chessBoard, lastMove);
                listener.OnMove(ChessPiece.BLACK, lastMove);
                DisplayBoard();
                winner = GetWinner(lastMove);

                lastMove = whitePlayer.MoveIn(chessBoard, lastMove);
                listener.OnMove(ChessPiece.WHITE, lastMove);
                DisplayBoard();
                winner = GetWinner(lastMove);

            }
            listener.OnGameEnd(winner);
            DisplayWinner(winner);
        }while(IsAbleToRestart());
    }

    public final void SetListener(@NotNull GameControllerListener listener){
        this.listener = listener;
    }

    protected abstract void DisplayBoard();

    protected abstract void DisplayWinner(ChessPiece winner);

    protected abstract boolean IsAbleToRestart();

    //TODO:Check whether the algorithm is correct
    public final ChessPiece GetWinner(@NotNull ChessBoard.Coordinate latestCoordinate){
        ChessPiece latestPiece = chessBoard.GetPiece(latestCoordinate);
        //search for 5 in a row by latestCoordinate
        int[] dx ={-1,-1,-1,0,1,1,1,0};
        int[] dy ={-1,0,1,1,1,0,-1,-1};
        int count=0;
        for(int direction=0;direction<8;direction++){
            ChessBoard.Coordinate delta = new ChessBoard.Coordinate(dx[direction],dy[direction]);
            ChessBoard.Coordinate backupCoordinate = latestCoordinate;
            count++;
            while(!chessBoard.IsNotAbleToQuery(backupCoordinate)&&count<5&&chessBoard.GetPiece(backupCoordinate)==latestPiece){
                backupCoordinate = backupCoordinate.Add(delta);
                count++;
            }
            if(count==5){
                return latestPiece;
            }
        }
        return ChessPiece.EMPTY;
    }
}

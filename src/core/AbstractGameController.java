package core;

import org.jetbrains.annotations.NotNull;

public abstract class AbstractGameController {
    private final ChessBoard chessBoard;
    private final AbstractPlayer blackPlayer;
    private final AbstractPlayer whitePlayer;

    protected AbstractGameController(int boardSize,AbstractPlayer blackPlayer,AbstractPlayer whitePlayer){
        chessBoard = new ChessBoard(boardSize);
        this.blackPlayer = blackPlayer;
        this.whitePlayer = whitePlayer;
    }

    protected AbstractGameController(){
        this(15,new FoolAIPlayer(ChessPiece.BLACK),new FoolAIPlayer(ChessPiece.WHITE));
    }

    public abstract void Start();

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

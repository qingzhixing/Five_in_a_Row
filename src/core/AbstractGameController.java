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
        this(15);
    }

    protected AbstractGameController(ChessBoard board){
        this.chessBoard = board;
    }

    protected AbstractGameController(int boardSize){
        this(boardSize,new FoolAIPlayer(ChessPiece.BLACK),new FoolAIPlayer(ChessPiece.WHITE));
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

                if(chessBoard.IsFull())break;
                lastMove = blackPlayer.MoveIn(chessBoard, lastMove);
                chessBoard.PlacePiece(lastMove, ChessPiece.BLACK);
                listener.OnMove(ChessPiece.BLACK, lastMove);
                DisplayBoard();
                winner = GetWinner(lastMove);

                if(chessBoard.IsFull())break;
                lastMove = whitePlayer.MoveIn(chessBoard, lastMove);
                chessBoard.PlacePiece(lastMove, ChessPiece.WHITE);
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
        System.out.println("----debug get winner----");
        System.out.println("latestCoordinate: " + latestCoordinate);
        System.out.println("lastPiece:"+chessBoard.GetPiece(latestCoordinate).toDetailString());

        ChessPiece latestPiece = chessBoard.GetPiece(latestCoordinate);
        //search for 5 in a row by latestCoordinate
        int[] dx ={-1,-1,-1,0,1,1,1,0};
        int[] dy ={-1,0,1,1,1,0,-1,-1};
        for(int direction=0;direction<8;direction++){
            int count=0;
            ChessBoard.Coordinate delta = new ChessBoard.Coordinate(dx[direction],dy[direction]);
            ChessBoard.Coordinate backupCoordinate = latestCoordinate;

            System.out.println("delta: " + delta);

            count++;
            while(!chessBoard.IsNotAbleToQuery(backupCoordinate)&&count<5){
                ChessPiece backupPiece = chessBoard.GetPiece(backupCoordinate);

                System.out.println("checking coordinate: " + backupCoordinate);
                System.out.println("backupPiece: " + backupPiece.toDetailString());

                if(backupPiece!=ChessPiece.EMPTY&& backupPiece==latestPiece){
                    backupCoordinate = backupCoordinate.Add(delta);
                    count++;
                }else{
                    break;
                }
            }
            if(count==5){
                return latestPiece;
            }
        }
        return ChessPiece.EMPTY;
    }
}

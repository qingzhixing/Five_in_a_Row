package qingzhixing.core.console;

import org.jetbrains.annotations.NotNull;

import static java.lang.Math.max;

public abstract class AbstractGameController {
    protected final ChessBoard chessBoard;
    protected AbstractPlayer blackPlayer;
    protected AbstractPlayer whitePlayer;

    protected AbstractGameController(int boardSize, AbstractPlayer blackPlayer, AbstractPlayer whitePlayer) {
        chessBoard = new ChessBoard(boardSize);
        this.blackPlayer = blackPlayer;
        this.whitePlayer = whitePlayer;
    }

    protected AbstractGameController() {
        this(15);
    }

    protected AbstractGameController(ChessBoard board) {
        this.chessBoard = board;
    }

    protected AbstractGameController(int boardSize) {
        this(boardSize, new RandomAIPlayer(ChessPiece.BLACK), new RandomAIPlayer(ChessPiece.WHITE));
    }

    public final ChessBoard GetChessBoard() {
        return chessBoard;
    }

    public void Start() {
        OnGameStart();
        DisplayBoard();
        do {
            //init
            chessBoard.Clear();

            OnRoundStart();

            ChessBoard.Coordinate lastMove = null;
            ChessPiece winner = ChessPiece.EMPTY;
            while (winner == ChessPiece.EMPTY) {

                if (chessBoard.IsFull()) break;
                lastMove = blackPlayer.MoveIn(chessBoard, lastMove);
                chessBoard.PlacePiece(lastMove, ChessPiece.BLACK);
                DisplayBoard();
                winner = GetWinner(lastMove);
                OnMove(ChessPiece.BLACK, lastMove);
                if (winner != ChessPiece.EMPTY) {
                    break;
                }

                if (chessBoard.IsFull()) break;
                lastMove = whitePlayer.MoveIn(chessBoard, lastMove);
                chessBoard.PlacePiece(lastMove, ChessPiece.WHITE);
                DisplayBoard();
                winner = GetWinner(lastMove);
                OnMove(ChessPiece.WHITE, lastMove);
            }
            OnRoundEnd(winner);
            DisplayWinner(winner);
        } while (IsAbleToRestart());
        OnGameEnd();
    }

    protected void OnGameEnd() {
    }

    protected void OnGameStart() {
    }

    protected void OnRoundStart() {
    }

    protected void OnRoundEnd(@NotNull ChessPiece winner) {
    }

    protected void OnMove(@NotNull ChessPiece black, @NotNull ChessBoard.Coordinate lastMove) {
    }


    protected abstract void DisplayBoard();

    protected abstract void DisplayWinner(@NotNull ChessPiece winner);

    protected abstract boolean IsAbleToRestart();

    //TODO:Check whether the algorithm is correct
    public final ChessPiece GetWinner(@NotNull ChessBoard.Coordinate latestCoordinate) {
        System.out.println("----debug get winner----");
        System.out.println("latestCoordinate: " + latestCoordinate);
        System.out.println("lastPiece:" + chessBoard.GetPiece(latestCoordinate).toDetailString());

        ChessPiece latestPiece = chessBoard.GetPiece(latestCoordinate);
        //search for 5 in a row by latestCoordinate
        int[] dx = {-1, -1, 0, 1};
        int[] dy = {0, 1, 1, 1};
        int maxCount = 0;
        for (int road = 0; road < 4; road++) {
            System.out.println("road: " + road);
            int count = 1;
            for (int direction = 1; direction <= 2; direction++) {
                System.out.println("direction: " + direction);
                ChessBoard.Coordinate delta;
                //calculate delta by the direction of the road
                if (direction == 1) {
                    delta = new ChessBoard.Coordinate(dx[road], dy[road]);
                } else {
                    delta = new ChessBoard.Coordinate(-dx[road], -dy[road]);
                }
                ChessBoard.Coordinate backupCoordinate = latestCoordinate;

                System.out.println("delta: " + delta);

                backupCoordinate = backupCoordinate.Add(delta);
                while (!chessBoard.CoordinateIsInvalid(backupCoordinate) && count < 5) {
                    ChessPiece backupPiece = chessBoard.GetPiece(backupCoordinate);

                    System.out.println("checking coordinate: " + backupCoordinate);
                    System.out.println("backupPiece: " + backupPiece.toDetailString());

                    if (backupPiece != ChessPiece.EMPTY && backupPiece == latestPiece) {
                        count++;
                        backupCoordinate = backupCoordinate.Add(delta);
                    } else {
                        break;
                    }

                    System.out.println("Success");
                }
            }
            System.out.println("count: " + count);
            maxCount = max(maxCount, count);
            System.out.println();
        }
        System.out.println("maxCount: " + maxCount);
        if (maxCount >= 5) {
            return latestPiece;
        }
        return ChessPiece.EMPTY;
    }
}
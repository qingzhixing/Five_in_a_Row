package qingzhixing.core.console;

import org.apache.log4j.Logger;
import org.jetbrains.annotations.NotNull;

import static java.lang.Math.max;

public abstract class AbstractGameController {

    private static final Logger logger = Logger.getLogger(AbstractGameController.class);
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

            Coordinate lastMove = null;
            ChessPiece winner = ChessPiece.EMPTY;
            while (winner == ChessPiece.EMPTY) {

                if (chessBoard.BoardIsFull()) break;
                lastMove = blackPlayer.MoveIn(chessBoard, lastMove);
                chessBoard.PlacePiece(lastMove, ChessPiece.BLACK);
                DisplayBoard();
                winner = GetWinner(lastMove);
                OnMove(ChessPiece.BLACK, lastMove);
                if (winner != ChessPiece.EMPTY) {
                    break;
                }

                if (chessBoard.BoardIsFull()) break;
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

    protected void OnMove(@NotNull ChessPiece player, @NotNull Coordinate lastMove) {
    }


    protected abstract void DisplayBoard();

    protected abstract void DisplayWinner(@NotNull ChessPiece winner);

    protected abstract boolean IsAbleToRestart();

    public final ChessPiece GetWinner(@NotNull Coordinate latestCoordinate) {
        logger.debug("GetWinner");
        logger.debug("latestCoordinate: " + latestCoordinate);
        logger.debug("lastPiece:" + chessBoard.GetPiece(latestCoordinate).toDetailString());

        ChessPiece latestPiece = chessBoard.GetPiece(latestCoordinate);
        //search for 5 in a row by latestCoordinate
        int[] dx = {-1, -1, 0, 1};
        int[] dy = {0, 1, 1, 1};
        int maxCount = 0;
        for (int road = 0; road < 4; road++) {
            logger.debug("road: " + road);
            int count = 1;
            for (int direction = 1; direction <= 2; direction++) {
                logger.debug("direction: " + direction);
                Coordinate delta;
                //calculate delta by the direction of the road
                if (direction == 1) {
                    delta = new Coordinate(dx[road], dy[road]);
                } else {
                    delta = new Coordinate(-dx[road], -dy[road]);
                }
                Coordinate backupCoordinate = latestCoordinate;

                logger.debug("delta: " + delta);

                backupCoordinate = backupCoordinate.Add(delta);
                while (!chessBoard.CoordinateIsInvalid(backupCoordinate) && count < 5) {
                    ChessPiece backupPiece = chessBoard.GetPiece(backupCoordinate);

                    logger.debug("checking coordinate: " + backupCoordinate);
                    logger.debug("backupPiece: " + backupPiece.toDetailString());

                    if (backupPiece != ChessPiece.EMPTY && backupPiece == latestPiece) {
                        count++;
                        backupCoordinate = backupCoordinate.Add(delta);
                    } else {
                        break;
                    }

                    logger.debug("Success");
                }
            }
            logger.debug("count: " + count + '\n');
            maxCount = max(maxCount, count);
        }
        logger.debug("maxCount: " + maxCount);
        if (maxCount >= 5) {
            return latestPiece;
        }
        return ChessPiece.EMPTY;
    }
}

package qingzhixing.core.console;

import org.apache.log4j.Logger;
import org.jetbrains.annotations.NotNull;

import java.util.Random;


/**
 * 棋盘坐标从1开始
 */
public class ChessBoard {
    private static final Logger logger = Logger.getLogger(ChessBoard.class);
    /**
     * value为估计胜率数组，仅在棋盘改变时更新
     */
    private long[][] value;
    private int size;
    private int colorChessAmount;
    private ChessPiece[][] board;

    public ChessBoard(int size) {
        this.size = size;
        board = new ChessPiece[size + 10][size + 10];
        value = new long[size + 10][size + 10];
        Clear();
    }

    public ChessBoard() {
        this(9);
    }

    /**
     * @return 返回一个随机合法坐标
     */
    public Coordinate GetRandomCoordinate() {
        Coordinate coordinate = new Coordinate(-1, -1);
        while (CoordinateIsInvalid(coordinate)) {
            int nextRaw = new Random().nextInt(size);
            int nextColumn = new Random().nextInt(size);
            coordinate = new Coordinate(nextRaw, nextColumn);
        }
        return coordinate;
    }

    public Coordinate GetRandomPlaceableCoordinate() {
        Coordinate coordinate = new Coordinate(-1, -1);
        if (IsNotPlaceable(coordinate) && !BoardIsFull()) {
            coordinate = GetRandomCoordinate();
        }
        return coordinate;
    }

    public void Clear() {
        for (int i = 1; i <= size; i++) {
            for (int j = 1; j <= size; j++) {
                board[i][j] = ChessPiece.EMPTY;
                value[i][j] = 4;
            }
        }
        colorChessAmount = 0;
    }

    public int GetSize() {
        return size;
    }

    public void Resize(int size) {
        this.size = size;
        board = new ChessPiece[size + 10][size + 10];
        value = new long[size + 10][size + 10];
        Clear();
    }

    /**
     * 放置棋子，AbstractPlayer::MoveInput不可调用
     *
     * @return 是否成功放置棋子
     */
    public boolean PlacePiece(@NotNull Coordinate coordinate, @NotNull ChessPiece piece) {
        if (IsNotPlaceable(coordinate)) {
            return false;
        }
        SetPiece(coordinate, piece);
        return true;
    }

    public void SetPiece(@NotNull Coordinate coordinate, @NotNull ChessPiece piece) {
        if (board[coordinate.row][coordinate.column] == ChessPiece.EMPTY) {
            colorChessAmount += ((piece == ChessPiece.EMPTY) ? 0 : 1);
        } else {
            colorChessAmount += ((piece == ChessPiece.EMPTY) ? -1 : 0);
        }
        board[coordinate.row][coordinate.column] = piece;
        UpdateValueArray();
    }

    public ChessPiece GetPiece(@NotNull Coordinate coordinate) throws IllegalArgumentException {
        if (CoordinateIsInvalid(coordinate)) {
            throw new IllegalArgumentException("The coordinate " + coordinate + " is not valid");
        }
        return board[coordinate.row][coordinate.column];
    }

    public boolean IsEmptyAt(@NotNull Coordinate coordinate) throws IllegalArgumentException {
        if (CoordinateIsInvalid(coordinate)) {
            throw new IllegalArgumentException("coordinate " + coordinate + " is out of range");
        }
        return board[coordinate.row][coordinate.column] == ChessPiece.EMPTY;
    }

    public boolean CoordinateIsInvalid(@NotNull Coordinate coordinate) {
        return coordinate.row <= 0 || coordinate.row > size || coordinate.column <= 0 || coordinate.column > size;
    }

    public boolean IsNotPlaceable(@NotNull Coordinate coordinate) {
        return CoordinateIsInvalid(coordinate) || !IsEmptyAt(coordinate);
    }

    /**
     * @return 棋盘是否能够下更多的棋子
     */
    public boolean BoardIsFull() {
        logger.debug("checking whether board is full");
        if (colorChessAmount >= size * size) {
            logger.debug("board is full");
        } else {
            logger.debug("board is not full");
        }
        return colorChessAmount >= size * size;
    }

    public void DebugPrintOnConsole() {
        for (int i = 1; i <= size; i++) {
            for (int j = 1; j <= size; j++) {
                System.out.print(board[i][j].toString() + '_');
            }
            System.out.println();
        }
    }

    /**
     * @param coordinate 棋盘上估值格子的坐标
     * @return 估值
     */
    private long Valuation(@NotNull Coordinate coordinate) {
        if (CoordinateIsInvalid(coordinate) || GetPiece(coordinate) != ChessPiece.EMPTY) {
            return 0;
        }

        int[] dx = {-1, -1, 0, 1};
        int[] dy = {0, 1, 1, 1};

        int whiteValue = 0;
        int blackValue = 0;

        for (int color = 1; color <= 2; color++) {
            ChessPiece checkPiece = color == 1 ? ChessPiece.WHITE : ChessPiece.BLACK;
            int currentColorValue = 0;

            for (int road = 0; road < 4; road++) {
                int pieceAmount = 0;

                for (int direction = 1; direction <= 2; direction++) {
                    Coordinate checkDirection = (
                            direction == 1 ?
                                    new Coordinate(dx[road], dy[road]) :
                                    new Coordinate(-dx[road], -dy[road])
                    );
                    Coordinate nextCoordinate = coordinate.clone().Add(checkDirection);
                    int step = 0;

                    while (!CoordinateIsInvalid(nextCoordinate) &&
                            GetPiece(nextCoordinate) == checkPiece &&
                            step <= 5) {
                        step++;
                        pieceAmount++;
                        nextCoordinate = nextCoordinate.Add(checkDirection);
                    }
                }

                currentColorValue += Math.pow(10, pieceAmount);
            }

            if (color == 1) {
                whiteValue += currentColorValue;
            } else {
                blackValue += currentColorValue;
            }
        }
        return Math.max(whiteValue, blackValue);
    }

    /**
     * 更新value数组，仅在棋盘改变时调用
     */
    private void UpdateValueArray() {
        for (int row = 1; row <= size; row++) {
            for (int col = 1; col <= size; col++) {
                value[row][col] = Valuation(new Coordinate(row, col));
            }
        }
    }

    /**
     * @return 返回value数组
     */
    public long[] @NotNull [] GetValueArray() {
        return value;
    }
}

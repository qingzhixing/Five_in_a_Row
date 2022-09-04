package qingzhixing.core.console;

import org.apache.log4j.Logger;
import org.jetbrains.annotations.NotNull;

import java.util.Random;

public class ChessBoard {
    private static final Logger logger = Logger.getLogger(ChessBoard.class);
    private int size;
    private int colorChessAmount;
    private ChessPiece[][] board;

    public ChessBoard(int size) {
        this.size = size;
        board = new ChessPiece[size + 10][size + 10];
        Clear();
    }

    public ChessBoard() {
        this(9);
    }

    public Coordinate GetRandomCoordinate() {
        Coordinate coordinate = new Coordinate(-1, -1);
        while (CoordinateIsInvalid(coordinate)) {
            int nextRaw = new Random().nextInt(size);
            int nextColumn = new Random().nextInt(size);
            coordinate = new Coordinate(nextRaw, nextColumn);
        }
        return coordinate;
    }

    public void Clear() {
        for (int i = 1; i <= size; i++) {
            for (int j = 1; j <= size; j++) {
                board[i][j] = ChessPiece.EMPTY;
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
        Clear();
    }

    /*
        false:failed to place the piece
        true:success to place the piece
     */
    public boolean PlacePiece(@NotNull Coordinate coordinate, @NotNull ChessPiece piece) {
        if (IsNotAbleToPlaceAnyPiece(coordinate)) {
            return false;
        }
        SetPiece(coordinate, piece);
        return true;
    }

    public void SetPiece(@NotNull Coordinate coordinate, @NotNull ChessPiece piece) {
        logger.debug("SetPiece,colorChessAmount turn from " + colorChessAmount);
        if (board[coordinate.row][coordinate.column] == ChessPiece.EMPTY) {
            colorChessAmount += ((piece == ChessPiece.EMPTY) ? 0 : 1);
        } else {
            colorChessAmount += ((piece == ChessPiece.EMPTY) ? -1 : 0);
        }
        logger.debug(" to " + colorChessAmount);
        board[coordinate.row][coordinate.column] = piece;
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

    public boolean IsNotAbleToPlaceAnyPiece(@NotNull Coordinate coordinate) {
        return CoordinateIsInvalid(coordinate) || !IsEmptyAt(coordinate);
    }

    public boolean IsFull() {
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

    public static class Coordinate {
        public int row;
        public int column;

        public Coordinate(int row, int column) {
            this.row = row;
            this.column = column;
        }

        public boolean equals(@NotNull Coordinate c) {
            return this.row == c.row && this.column == c.column;
        }

        public @NotNull Coordinate Add(@NotNull Coordinate other) {
            return new Coordinate(this.row + other.row, this.column + other.column);
        }

        public @NotNull Coordinate Subtract(@NotNull Coordinate other) {
            return new Coordinate(this.row - other.row, this.column - other.column);
        }

        @Override
        public String toString() {
            return "(" + row + "," + column + ")";
        }
    }
}

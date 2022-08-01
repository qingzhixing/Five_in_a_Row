package core;

import org.jetbrains.annotations.NotNull;

public class ChessBoard {
    public static class Coordinate{
        int row;
        int column;
        public Coordinate(int row, int column){
            this.row = row;
            this.column = column;
        }

        public boolean equals(@NotNull Coordinate c){
            return this.row == c.row && this.column == c.column;
        }

        public @NotNull Coordinate Add(@NotNull Coordinate other){
            return new Coordinate(this.row + other.row, this.column + other.column);
        }

        public @NotNull Coordinate Subtract(@NotNull Coordinate other){
            return new Coordinate(this.row - other.row, this.column - other.column);
        }
    }
    private int size;
    private ChessPiece[][] board;

    public ChessBoard(int size) {
        this.size = size;
        board = new ChessPiece[size][size];
    }

    public ChessBoard() {
        this(9);
    }

    public void Clear() {
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                board[i][j] = ChessPiece.EMPTY;
            }
        }
    }

    public int GetSize(){
        return size;
    }

    public void Resize(int size) {
        this.size = size;
        board = new ChessPiece[size][size];
    }

    /*
        false:failed to place the piece
        true:success to place the piece
     */
    public boolean PlacePiece(@NotNull Coordinate coordinate,ChessPiece piece) {
        int row=coordinate.row;
        int column=coordinate.column;
        if(!IsAbleToPlace(coordinate)){
            return false;
        }
        board[row][column] = piece;
        return true;
    }

    public void SetPiece(@NotNull Coordinate coordinate, ChessPiece piece) {
        board[coordinate.row][coordinate.column] = piece;
    }

    public ChessPiece GetPiece(@NotNull Coordinate coordinate) {
        return board[coordinate.row][coordinate.column];
    }

    public boolean IsEmptyAt(@NotNull Coordinate coordinate) throws IllegalArgumentException {
        if (coordinate.row < 0 || coordinate.row >= size || coordinate.column < 0 || coordinate.column >= size) {
            throw new IllegalArgumentException("coordinate is out of range");
        }
        return board[coordinate.row][coordinate.column] == ChessPiece.EMPTY;
    }

    public boolean IsAbleToPlace(@NotNull Coordinate coordinate) {
        return coordinate.row >= 0 && coordinate.row < size &&
                coordinate.column >= 0 && coordinate.column < size &&
                IsEmptyAt(coordinate);
    }

}

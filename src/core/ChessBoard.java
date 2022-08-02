package core;

import org.jetbrains.annotations.NotNull;

import java.util.Random;

public class ChessBoard {
    public Coordinate GetRandomCoordinate() {
        Coordinate coordinate = new Coordinate(-1, -1);
        while(IsNotAbleToQuery(coordinate)){
            int nextRaw = new Random().nextInt(size);
            int nextColumn = new Random().nextInt(size);
            coordinate = new Coordinate(nextRaw, nextColumn);
        }
        return coordinate;
    }

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

        @Override
        public String toString(){
            return "(" + row + "," + column + ")";
        }
    }
    private int size;
    private int colorChessAmount;
    private ChessPiece[][] board;

    public ChessBoard(int size) {
        this.size = size;
        board = new ChessPiece[size][size];
        Clear();
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
        colorChessAmount = 0;
    }

    public int GetSize(){
        return size;
    }

    public void Resize(int size) {
        this.size = size;
        board = new ChessPiece[size][size];
        Clear();
    }

    /*
        false:failed to place the piece
        true:success to place the piece
     */
    public boolean PlacePiece(@NotNull Coordinate coordinate,@NotNull ChessPiece piece) {
        if(IsNotAbleToPlaceAnyPiece(coordinate)){
            return false;
        }
        SetPiece(coordinate, piece);
        return true;
    }

    public void SetPiece(@NotNull Coordinate coordinate,@NotNull ChessPiece piece) {
        System.out.print("SetPiece,colorChessAmount turn from "+colorChessAmount);
        if(board[coordinate.row][coordinate.column] == ChessPiece.EMPTY){
            colorChessAmount += ((piece == ChessPiece.EMPTY)? 0 : 1);
        }else{
            colorChessAmount += ((piece == ChessPiece.EMPTY)? -1 : 0);
        }
        System.out.println(" to "+colorChessAmount);
        board[coordinate.row][coordinate.column] = piece;
    }

    public ChessPiece GetPiece(@NotNull Coordinate coordinate)throws IllegalArgumentException {
        if(IsNotAbleToQuery(coordinate)){
            throw new IllegalArgumentException("The coordinate is not valid");
        }
        return board[coordinate.row][coordinate.column];
    }

    public boolean IsEmptyAt(@NotNull Coordinate coordinate) throws IllegalArgumentException {
        if (IsNotAbleToQuery(coordinate)) {
            throw new IllegalArgumentException("coordinate is out of range");
        }
        return board[coordinate.row][coordinate.column] == ChessPiece.EMPTY;
    }

    public boolean IsNotAbleToQuery(@NotNull Coordinate coordinate){
        return coordinate.row < 0 || coordinate.row >= size || coordinate.column < 0 || coordinate.column >= size;
    }

    public boolean IsNotAbleToPlaceAnyPiece(@NotNull Coordinate coordinate) {
        return IsNotAbleToQuery(coordinate)|| !IsEmptyAt(coordinate);
    }

    public boolean IsFull(){
        return colorChessAmount >= size * size;
    }

    public void DebugPrintOnConsole(){
        for(int i=0;i<size;i++){
            for(int j=0;j<size;j++){
                System.out.print(board[i][j].toString());
            }
            System.out.println();
        }
    }
}

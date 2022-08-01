package core;

public class ChessBoard {
    public static class Coordinate{
        int row;
        int column;
        public Coordinate(int row, int column){
            this.row = row;
            this.column = column;
        }

        public boolean equals(Coordinate c){
            return this.row == c.row && this.column == c.column;
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

    public void Resize(int size) {
        this.size = size;
        board = new ChessPiece[size][size];
    }

    /*
        false:failed to place the piece
        true:success to place the piece
     */
    public boolean PlacePiece(Coordinate coordinate, ChessPiece piece) {
        int row=coordinate.row;
        int col=coordinate.column;
        if (row < 0 || row >= size || col < 0 || col >= size) {
            return false;
        }
        if (board[row][col] != ChessPiece.EMPTY && piece != ChessPiece.EMPTY) {
            return false;
        }
        board[row][col] = piece;
        return true;
    }

    public void SetPiece(int row, int col, ChessPiece piece) {
        board[row][col] = piece;
    }

}

package qingzhixing.core.console;

public class RandomAIPlayer extends AbstractPlayer {
    public RandomAIPlayer(ChessPiece piece) {
        super(piece);
    }

    public RandomAIPlayer(String name, ChessPiece piece) {
        super(name, piece);
    }

    @Override
    public ChessBoard.Coordinate MoveIn(ChessBoard board, ChessBoard.Coordinate lastMove) {
        ChessBoard.Coordinate randomCoordinate = board.GetRandomCoordinate();
        while (board.IsNotAbleToPlaceAnyPiece(randomCoordinate)) {
            randomCoordinate = board.GetRandomCoordinate();
        }
        return randomCoordinate;
    }
}

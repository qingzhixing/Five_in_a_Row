package qingzhixing.core.console;

public class RandomAIPlayer extends AbstractPlayer {
    public RandomAIPlayer(ChessPiece ownPiece) {
        super((ownPiece == ChessPiece.BLACK ? "black" : "white"), ownPiece);
    }

    public RandomAIPlayer(String name, ChessPiece piece) {
        super(name, piece);
    }

    @Override
    public Coordinate MoveIn(ChessBoard board, Coordinate counterpartyLastMove) {
        return board.GetRandomPlaceableCoordinate();
    }
}

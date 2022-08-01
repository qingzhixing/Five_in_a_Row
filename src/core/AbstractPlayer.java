package core;

public abstract class AbstractPlayer {
    public final ChessPiece ownPiece;

    public String name;

    AbstractPlayer(String name, ChessPiece ownPiece) {
        this.name = name;
        this.ownPiece = ownPiece;
    }

    AbstractPlayer(ChessPiece ownPiece) {
        this("Unnamed core.Player", ownPiece);
    }

    public abstract ChessBoard.Coordinate MoveIn(ChessBoard board,ChessBoard.Coordinate counterpartyLastMove);
}

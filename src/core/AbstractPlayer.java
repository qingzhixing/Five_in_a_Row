package core;

public abstract class AbstractPlayer {
    public final ChessPiece ownPiece;

    public String name;

    protected AbstractPlayer(String name, ChessPiece ownPiece) {
        this.name = name;
        this.ownPiece = ownPiece;
    }

    protected AbstractPlayer(ChessPiece ownPiece) {
        this("Unnamed core.Player", ownPiece);
    }

    public abstract ChessBoard.Coordinate MoveIn(ChessBoard board,ChessBoard.Coordinate counterpartyLastMove);
}

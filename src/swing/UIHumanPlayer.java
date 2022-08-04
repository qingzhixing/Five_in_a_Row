package swing;

import core.AbstractPlayer;
import core.ChessBoard;
import core.ChessPiece;

public class UIHumanPlayer extends AbstractPlayer{
    protected UIHumanPlayer(String name, ChessPiece ownPiece) {
        super(name, ownPiece);
    }

    protected UIHumanPlayer(ChessPiece ownPiece) {
        super(ownPiece);
    }

    @Override
    public ChessBoard.Coordinate MoveIn(ChessBoard board, ChessBoard.Coordinate counterpartyLastMove) {
        return null;
    }
}

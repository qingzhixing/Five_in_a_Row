package qingzhixing.core.console;

import org.jetbrains.annotations.NotNull;

import java.util.Random;

public class FoolAIPlayer extends AbstractPlayer {

    FoolAIPlayer(String name, ChessPiece ownPiece) {
        super(name, ownPiece);
    }

    FoolAIPlayer(ChessPiece ownPiece) {
        super(ownPiece);
    }

    @Override
    public Coordinate MoveIn(@NotNull ChessBoard board, Coordinate counterpartyLastMove) {
        /*
            永远在对手左上方左上方放置棋子，否则随机放置
         */
        if (counterpartyLastMove == null) {
            counterpartyLastMove = new Coordinate(-1, -1);
        }
        Coordinate coordinate = counterpartyLastMove.Subtract(new Coordinate(1, 1));
        while (board.IsNotAbleToPlaceAnyPiece(coordinate)) {
            int newRaw = new Random().nextInt(board.GetSize());
            int newCol = new Random().nextInt(board.GetSize());
            coordinate = new Coordinate(newRaw, newCol);
        }
        return coordinate;
    }
}

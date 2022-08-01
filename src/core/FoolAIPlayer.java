package core;

import org.jetbrains.annotations.NotNull;

import java.util.Random;

public class FoolAIPlayer extends AbstractPlayer{

    FoolAIPlayer(String name, ChessPiece ownPiece) {
        super(name, ownPiece);
    }

    FoolAIPlayer(ChessPiece ownPiece) {
        super(ownPiece);
    }

    @Override
    public ChessBoard.Coordinate MoveIn(@NotNull ChessBoard board, ChessBoard.Coordinate counterpartyLastMove) {
        /*
            永远在对手左上方左上方放置棋子，否则随机放置
         */
        ChessBoard.Coordinate coordinate = counterpartyLastMove.Subtract(new ChessBoard.Coordinate(1, 1));
        while(board.IsNotAbleToPlace(coordinate)){
            int newRaw=new Random().nextInt(board.GetSize());
            int newCol=new Random().nextInt(board.GetSize());
            coordinate = new ChessBoard.Coordinate(newRaw, newCol);
        }
        return coordinate;
    }
}

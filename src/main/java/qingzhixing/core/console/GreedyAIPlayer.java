package qingzhixing.core.console;

public class GreedyAIPlayer extends AbstractPlayer {
    protected GreedyAIPlayer(String name, ChessPiece ownPiece) {
        super(name, ownPiece);
    }

    public GreedyAIPlayer(ChessPiece ownPiece) {
        super((ownPiece == ChessPiece.BLACK ? "black" : "white"), ownPiece);
    }

    @Override
    public Coordinate MoveIn(ChessBoard board, Coordinate counterpartyLastMove) {
        //没有棋子则随机下
        if (counterpartyLastMove == null) {
            return board.GetRandomPlaceableCoordinate();
        }

        int boardSize = board.GetSize();
        long[][] value = board.GetValueArray();
        Coordinate valueMaxCoordinate = new Coordinate(-1, -1);
        long maxValue = 4;

        for (int row = 1; row <= boardSize; row++) {
            for (int column = 1; column <= boardSize; column++) {
                long currentValue = value[row][column];
                if (currentValue > maxValue) {
                    maxValue = currentValue;
                    valueMaxCoordinate = new Coordinate(row, column);
                }
            }
        }

        return valueMaxCoordinate;
    }
}

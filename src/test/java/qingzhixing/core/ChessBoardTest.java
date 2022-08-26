package qingzhixing.core;


import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import qingzhixing.core.console.ChessBoard;
import qingzhixing.core.console.ChessPiece;

import java.util.Random;

public class ChessBoardTest {
    @Test
    public void test() {
        System.out.println("ChessBoardTest");
        ChessBoard board = new ChessBoard(15);
        int pieceAmount = new Random().nextInt(board.GetSize() * board.GetSize());

        System.out.println("Piece Amount: " + pieceAmount);

        int randomRaw = -1;
        int randomColumn = -1;
        for (int i = 1; i <= pieceAmount; i++) {
            System.out.println("Placing Piece: " + i);
            while (!board.PlacePiece(new ChessBoard.Coordinate(randomRaw, randomColumn), ChessPiece.RandomPiece())) {
                randomRaw = new Random().nextInt(board.GetSize());
                randomColumn = new Random().nextInt(board.GetSize());
                System.out.println("Piece not placed, trying (" + randomRaw + "," + randomColumn + ")");
            }
        }
        board.DebugPrintOnConsole();
        Assertions.assertTrue(true);
    }
}

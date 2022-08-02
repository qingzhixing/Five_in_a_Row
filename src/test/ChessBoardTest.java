import core.ChessBoard;
import core.ChessPiece;

import java.util.Random;

public class ChessBoardTest {
    public static void main(String[] args) {
        System.out.println("ChessBoardTest");
        ChessBoard board = new ChessBoard(15);
        int pieceAmount= new Random().nextInt(board.GetSize()*board.GetSize());

        System.out.println("Piece Amount: " + pieceAmount);

        int randomRaw=-1;
        int randomColumn=-1;
        for(int i=1;i<=pieceAmount;i++){
            System.out.println("Placing Piece: " + i);
            while(!board.PlacePiece(new ChessBoard.Coordinate(randomRaw,randomColumn), ChessPiece.RandomPiece())){
                randomRaw=new Random().nextInt(board.GetSize());
                randomColumn=new Random().nextInt(board.GetSize());
                System.out.println("Piece not placed, trying (" + randomRaw + "," + randomColumn + ")");
            }
        }
        board.DebugPrintOnConsole();
    }
}

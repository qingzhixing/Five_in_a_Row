package qingzhixing.core.console;

import java.util.Scanner;

public class DebugConsoleHumanPlayer extends AbstractPlayer {
    protected DebugConsoleHumanPlayer(String name, ChessPiece ownPiece) {
        super(name, ownPiece);
    }

    public DebugConsoleHumanPlayer(ChessPiece ownPiece) {
        super(ownPiece);
    }

    @Override
    public Coordinate MoveIn(ChessBoard board, Coordinate counterpartyLastMove) {
        System.out.println("Enter your move: ");
        Scanner scanner = new Scanner(System.in);
        if (scanner.hasNextInt()) {
            int x = scanner.nextInt();
            int y = scanner.nextInt();
            return new Coordinate(x, y);
        }
        return null;
    }
}
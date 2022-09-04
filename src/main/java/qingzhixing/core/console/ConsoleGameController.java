package qingzhixing.core.console;

import org.apache.log4j.Logger;
import org.jetbrains.annotations.NotNull;

import java.util.Scanner;

public class ConsoleGameController extends AbstractGameController {
    private static final Logger logger = Logger.getLogger(ConsoleGameController.class);

    public ConsoleGameController(int boardSize, AbstractPlayer blackPlayer, AbstractPlayer whitePlayer) {
        super(boardSize, blackPlayer, whitePlayer);
    }

    public ConsoleGameController() {
        super();
    }

    public ConsoleGameController(ChessBoard board) {
        super(board);
    }

    public ConsoleGameController(int boardSize) {
        super(boardSize);
    }

    @Override
    protected void DisplayBoard() {
        logger.debug("----display board----");
        chessBoard.DebugPrintOnConsole();
    }

    @Override
    protected void DisplayWinner(@NotNull ChessPiece winner) {
        logger.debug("----display winner----");
        if (winner == ChessPiece.EMPTY) {
            System.out.println("There's no winner");
        } else {
            System.out.println("Winner is " + winner.name());
        }
    }

    @Override
    protected boolean IsAbleToRestart() {
        boolean isAbleToRestart = false;
        Scanner scanner = new Scanner(System.in);
        System.out.println("Do you want to restart?(y/n)");
        if (scanner.hasNext()) {
            String input = scanner.next();
            isAbleToRestart = input.equals("y");
        }

        return isAbleToRestart;
    }

    @Override
    protected void OnMove(@NotNull ChessPiece player, ChessBoard.@NotNull Coordinate coordinate) {
//        new Scanner(System.in).nextLine();
    }

    @Override
    protected void OnGameEnd() {
        logger.info("----OnGameEnd----");
    }
}

package core;

import java.util.Scanner;

public class ConsoleGameController extends AbstractGameController{
    public ConsoleGameController(int boardSize,AbstractPlayer blackPlayer,AbstractPlayer whitePlayer){
        super(boardSize,blackPlayer,whitePlayer);
    }
    public ConsoleGameController(){
        super();
    }
    public ConsoleGameController(ChessBoard board){
        super(board);
    }

    @Override
    protected void DisplayBoard() {
        System.out.println("----display board----");
        chessBoard.DebugPrintOnConsole();
    }

    @Override
    protected void DisplayWinner(ChessPiece winner) {
        System.out.println("----display winner----");
        System.out.println("Winner is " + winner);
    }

    @Override
    protected boolean IsAbleToRestart(){
        boolean isAbleToRestart=false;
        Scanner scanner = new Scanner(System.in);
        System.out.println("Do you want to restart?(y/n)");
        if(scanner.hasNext()){
            isAbleToRestart= scanner.next().equals("y");
        }
        scanner.close();
        return isAbleToRestart;
    }
}

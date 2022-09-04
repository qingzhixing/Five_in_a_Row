package qingzhixing.core.swing;

import org.apache.log4j.Logger;
import qingzhixing.core.console.AbstractPlayer;
import qingzhixing.core.console.ChessBoard;
import qingzhixing.core.console.ChessPiece;

import java.util.ArrayList;
import java.util.concurrent.atomic.AtomicReference;

public class UIHumanPlayer extends AbstractPlayer {
    private static final Logger logger = Logger.getLogger(UIHumanPlayer.class);
    private ArrayList<ChessBoard.Coordinate> bindMouseClicked;

    protected UIHumanPlayer(String name, ChessPiece ownPiece) {
        super(name, ownPiece);
    }

    protected UIHumanPlayer(ChessPiece ownPiece) {
        super(ownPiece);
    }

    public void BindMouseClickedList(ArrayList<ChessBoard.Coordinate> bindMouseClicked) {
        this.bindMouseClicked = bindMouseClicked;
    }

    @Override
    public ChessBoard.Coordinate MoveIn(ChessBoard board, ChessBoard.Coordinate counterpartyLastMove) {
        if (bindMouseClicked == null) {
            return null;
        }
        bindMouseClicked.clear();
        AtomicReference<ChessBoard.Coordinate> move = new AtomicReference<>();
        while (move.get() == null) {
            bindMouseClicked.forEach(coordinate -> {
                if (!board.IsNotAbleToPlaceAnyPiece(coordinate)) {
                    move.set(coordinate);
                }
            });
        }
        logger.debug("MoveIn() called:" + move.get());
        return move.get();
    }
}

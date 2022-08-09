package swing;

import core.ChessBoard;
import org.jetbrains.annotations.NotNull;

import javax.swing.*;

public class GameComponent extends JComponent {
    int gridSideLength;

    int padding;

    ChessBoard board;

    public GameComponent(int gridSideLength, int padding,@NotNull ChessBoard board) {
        this.gridSideLength = gridSideLength;
        this.padding = padding;
        this.board = board;
    }
}

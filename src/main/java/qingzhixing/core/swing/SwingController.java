package qingzhixing.core.swing;

import org.jetbrains.annotations.NotNull;
import qingzhixing.core.console.ChessBoard;

public class SwingController {
    GameFrame frame;
    GameComponent component;

    public SwingController(int gridSideLength, int padding, @NotNull ChessBoard board) {
        this.frame = new GameFrame(board.GetSize(), gridSideLength, padding);
        this.component = new GameComponent(gridSideLength, padding, board);
        this.frame.add(component);
    }
}

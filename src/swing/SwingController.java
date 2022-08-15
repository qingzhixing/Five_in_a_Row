package swing;

import core.ChessBoard;
import org.jetbrains.annotations.NotNull;

public class SwingController {
    GameFrame frame;
    GameComponent component;

    public SwingController(int gridSideLength, int padding, @NotNull ChessBoard board) {
        this.frame = new GameFrame(board.GetSize(), gridSideLength, padding);
        this.component = new GameComponent(gridSideLength, padding, board);
        this.frame.add(component);
    }
}

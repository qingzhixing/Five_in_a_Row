package swing;

import core.ChessBoard;
import org.jetbrains.annotations.NotNull;

public class SwingController {
    GameFrame frame;

    public SwingController(int gridSideLength,int padding,@NotNull ChessBoard board) {
        this.frame = new GameFrame(board.GetSize(),gridSideLength,padding);
        this.frame.add(new GameComponent(gridSideLength,padding,board));
    }


}

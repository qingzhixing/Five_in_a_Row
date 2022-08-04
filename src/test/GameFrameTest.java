import swing.GameFrame;

import java.awt.*;
import java.io.IOException;

public class GameFrameTest {
    public static void main(String[] args) throws IOException {
        GameFrame frame = new GameFrame(6,50,10);
        EventQueue.invokeLater(()->{
            frame.setVisible(true);
        });
    }
}

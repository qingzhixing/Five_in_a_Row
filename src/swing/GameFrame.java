package swing;

import javax.swing.*;
import java.io.IOException;
import java.util.Objects;

public class GameFrame extends JFrame {

    /*
    根据棋盘大小，格子变长，内间距计算窗口大小
     */
    public GameFrame(int boardSize, int gridSideLength, int padding) {
        int length = gridSideLength * (boardSize + 1) + padding * 2;
        setSize(length, length);
        setResizable(false);
        setTitle("Five in a Row Game");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

    }
}
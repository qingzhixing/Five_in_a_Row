package qingzhixing.core.swing;

import javax.swing.*;

public class GameFrame extends JFrame {

    /*
    根据棋盘大小，格子变长，内间距计算窗口大小
     */
    public GameFrame(int boardSize, int gridSideLength, int padding) {
        int length = gridSideLength * boardSize + padding * 2;
        setSize(length, length + 50);
        setResizable(false);
        setTitle("Five in a Row Game");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }
}
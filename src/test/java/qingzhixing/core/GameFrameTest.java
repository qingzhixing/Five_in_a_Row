package qingzhixing.core;

import qingzhixing.core.swing.GameFrame;

import java.awt.*;

public class GameFrameTest {
    public static void main(String[] args) {
        GameFrame frame = new GameFrame(6, 50, 10);
        EventQueue.invokeLater(() -> {
            frame.setVisible(true);
        });
    }
}

package qingzhixing.core.swing;

import org.apache.log4j.Logger;

import javax.swing.*;
import java.awt.*;
import java.io.File;
import java.util.Objects;

public class GameFrame extends JFrame {
    private static final Logger logger = Logger.getLogger(GameFrame.class);

    /*
    根据棋盘大小，格子变长，内间距计算窗口大小
     */
    public GameFrame(int boardSize, int gridSideLength, int padding) {
        int length = gridSideLength * boardSize + padding * 2;
        setSize(length, length + 50);
        setResizable(false);
        setTitle("Five in a Row Game");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        //load icon
        String rootPath = Objects.requireNonNull(GameFrame.class.getResource("/")).getPath();
        String iconPath = rootPath + "chess.png";
        logger.debug("icon path: " + iconPath);
        if (!new File(iconPath).exists()) {
            logger.error("Icon file does not exists");
        } else {
            Image icon = new ImageIcon(iconPath).getImage();
            setIconImage(icon);
        }
    }
}
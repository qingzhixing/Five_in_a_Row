package swing;

import javax.swing.*;
import java.io.IOException;
import java.util.Objects;

public class GameFrame extends JFrame {

    /*
    根据棋盘大小，格子变长，内间距计算窗口大小
     */
    public GameFrame(int boardSize, int gridSideLength, int padding) throws IOException {
        int length = gridSideLength*(boardSize+1)+padding*2;
        setSize(length, length);
        setResizable(false);
        setTitle("Five in a Row Game");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        //debug get working directory
        String workingDir = System.getProperty("user.dir");

        //check /assets/chess.png is existed in working directory
        if(!new java.io.File(workingDir+"/assets/chess.png").exists()) {
            throw new IOException("/assets/chess.png is not existed in working directory");
        }else{
            System.out.println("/assets/chess.png is existed in working directory");
        }
        //read chess.png from working directory
        ImageIcon chessIcon = new ImageIcon(workingDir+"/assets/chess.png");
        setIconImage(chessIcon.getImage());
    }
}

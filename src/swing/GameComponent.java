package swing;

import core.ChessBoard;
import core.ChessPiece;
import org.jetbrains.annotations.NotNull;

import javax.swing.*;
import java.awt.*;
import java.awt.geom.Ellipse2D;

public class GameComponent extends JComponent {
    static int pieceRadius=2;

    int gridSideLength;

    int padding;

    ChessBoard board;

    public GameComponent(int gridSideLength, int padding,@NotNull ChessBoard board) {
        this.gridSideLength = gridSideLength;
        this.padding = padding;
        this.board = board;
    }

    @Override
    protected void paintComponent(Graphics g) {
        Graphics2D g2d = (Graphics2D) g;
        DrawBackground(g2d);
        DrawGrid(g2d);
        DrawPieces(g2d);
    }

    private void DrawPieces(Graphics2D g2d) {
        int boardSize = board.GetSize();
        for (int i = 0; i < boardSize; i++) {
            for (int j = 0; j < boardSize; j++) {
                ChessPiece piece = board.GetPiece(new ChessBoard.Coordinate(i, j));
                if (piece != ChessPiece.EMPTY) {
                    DrawPiece(g2d, piece, i, j);
                }
            }
        }
    }

    private void DrawPiece(Graphics2D g2d, ChessPiece piece, int i, int j) {
        int leftTopX = padding + i * gridSideLength-pieceRadius;
        int leftTopY = padding + j * gridSideLength-pieceRadius;
        Ellipse2D.Double circle = new Ellipse2D.Double(leftTopX, leftTopY, 2 * pieceRadius,2 * pieceRadius);
        Color color;
        if (piece == ChessPiece.BLACK) {
            color = Color.BLACK;
        } else {
            color = Color.WHITE;
        }
        g2d.setColor(color);
        g2d.fill(circle);
    }

    private void DrawGrid(Graphics2D g2d) {
        g2d.setColor(Color.MAGENTA);
        int boardSize = board.GetSize();
        //horizontal lines
        for(int currentY=padding;currentY<=boardSize*gridSideLength+padding;currentY+=gridSideLength){
            g2d.drawLine(padding,currentY,boardSize*gridSideLength+padding,currentY);
        }
        //vertical lines
        for(int currentX=padding;currentX<=boardSize*gridSideLength+padding;currentX+=gridSideLength){
            g2d.drawLine(currentX,padding,currentX,boardSize*gridSideLength+padding);
        }
    }

    private void DrawBackground(Graphics2D g2d) {
        g2d.setColor(Color.CYAN);
        g2d.fillRect(0, 0, getWidth(), getHeight());
    }
}

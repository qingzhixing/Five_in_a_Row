package swing;

import core.ChessBoard;
import core.ChessPiece;
import org.jetbrains.annotations.NotNull;

import javax.swing.*;
import java.awt.*;
import java.awt.geom.Ellipse2D;

public class GameComponent extends JComponent {
    static int pieceRadius = 9;

    int gridSideLength;

    int padding;

    ChessBoard board;

    Graphics2D bindG2d;

    public GameComponent(int gridSideLength, int padding, @NotNull ChessBoard board) {
        this.gridSideLength = gridSideLength;
        this.padding = padding;
        this.board = board;
    }

    @Override
    protected void paintComponent(Graphics g) {
        bindG2d = (Graphics2D) g;
        DrawBackground();
        DrawGrid();
        DrawPieces();
        DebugDrawCoordinates();
    }

    private void DebugDrawCoordinates() {
        bindG2d.setColor(Color.red);
        Font currentFont = bindG2d.getFont();
        Font newFont = new Font(currentFont.getFontName(), currentFont.getStyle(), currentFont.getSize() - 5);
        bindG2d.setFont(newFont);

        int boardSize = board.GetSize();
        for (int i = 1; i <= boardSize; i++) {
            for (int j = 1; j <= boardSize; j++) {
                float gridX = (i - 1) * gridSideLength + padding;
                float gridY = (j - 1) * gridSideLength + padding + 0.5f * gridSideLength;
                bindG2d.drawString("(" + i + "," + j + ")", gridX, gridY);
            }
        }

        bindG2d.setFont(currentFont);
    }

    private void DrawPieces() {
        int boardSize = board.GetSize();
        for (int i = 1; i <= boardSize; i++) {
            for (int j = 1; j <= boardSize; j++) {
                ChessPiece piece = board.GetPiece(new ChessBoard.Coordinate(i, j));
                if (piece != ChessPiece.EMPTY) {
                    DrawPiece(piece, i, j);
                }
            }
        }
    }

    private void DrawPiece(ChessPiece piece, int i, int j) {
        double leftTopX = padding + i * gridSideLength - pieceRadius - 0.5 * gridSideLength;
        double leftTopY = padding + j * gridSideLength - pieceRadius - 0.5 * gridSideLength;
        Ellipse2D.Double circle = new Ellipse2D.Double(leftTopX, leftTopY, 2 * pieceRadius, 2 * pieceRadius);
        Color color;
        if (piece == ChessPiece.BLACK) {
            color = Color.BLACK;
        } else {
            color = Color.WHITE;
        }
        bindG2d.setColor(color);
        bindG2d.fill(circle);
    }

    private void DrawGrid() {
        bindG2d.setColor(Color.MAGENTA);
        int boardSize = board.GetSize();
        //horizontal lines
        for (int currentY = padding; currentY <= boardSize * gridSideLength + padding; currentY += gridSideLength) {
            bindG2d.drawLine(padding, currentY, boardSize * gridSideLength + padding, currentY);
        }
        //vertical lines
        for (int currentX = padding; currentX <= boardSize * gridSideLength + padding; currentX += gridSideLength) {
            bindG2d.drawLine(currentX, padding, currentX, boardSize * gridSideLength + padding);
        }
    }

    private void DrawBackground() {
        bindG2d.setColor(Color.CYAN);
        bindG2d.fillRect(0, 0, getWidth(), getHeight());
    }
}

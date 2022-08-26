package qingzhixing.core.swing;

import org.jetbrains.annotations.NotNull;
import qingzhixing.core.console.ChessBoard;
import qingzhixing.core.console.ChessPiece;

import javax.swing.*;
import javax.swing.event.MouseInputListener;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.geom.Ellipse2D;
import java.util.ArrayList;

public class GameComponent extends JComponent implements MouseInputListener {
    private static final int pieceRadius = 9;
    public final ArrayList<ChessBoard.Coordinate> mouseClicked = new ArrayList<>();
    private final int gridSideLength;
    private final int padding;
    private final ChessBoard board;
    private Graphics2D bindG2d;
    private ChessBoard.Coordinate mouseCoordinate = new ChessBoard.Coordinate(-1, -1);

    public GameComponent(int gridSideLength, int padding, @NotNull ChessBoard board) {
        this.gridSideLength = gridSideLength;
        this.padding = padding;
        this.board = board;
        //add mouse listeners
        this.addMouseListener(this);
        this.addMouseMotionListener(this);
    }

    @Override
    protected void paintComponent(Graphics g) {
        bindG2d = (Graphics2D) g;
        DrawBackground();
        DrawGrid();
        DrawPieces();
        DrawMouseCoordinate();
        DebugDrawCoordinates();
    }

    private void DrawMouseCoordinate() {
        if (mouseCoordinate == null || board.CoordinateIsInvalid(mouseCoordinate)) return;
        final int mouseGridSizeLength = gridSideLength - 2;
        int centerX = (mouseCoordinate.row - 1) * gridSideLength + padding + gridSideLength / 2;
        int centerY = (mouseCoordinate.column - 1) * gridSideLength + padding + gridSideLength / 2;
        int leftTopX = centerX - mouseGridSizeLength / 2;
        int leftTopY = centerY - mouseGridSizeLength / 2;
        Rectangle rectangle = new Rectangle(leftTopX, leftTopY, mouseGridSizeLength, mouseGridSizeLength);
        bindG2d.setColor(Color.BLUE);
        bindG2d.draw(rectangle);
        mouseCoordinate = new ChessBoard.Coordinate(-1, -1);
//        System.out.println("DrawMouseCoordinate() called");
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

    private @NotNull ChessBoard.Coordinate MousePositionToGridCoordinate(int x, int y) {
        if (MousePositionIsInvalid(x, y)) {
            return new ChessBoard.Coordinate(-1, -1);
        }
        int coordinateX = (x - padding) / gridSideLength + 1;
        int coordinateY = (y - padding) / gridSideLength + 1;
        return new ChessBoard.Coordinate(coordinateX, coordinateY);
    }

    private boolean MousePositionIsInvalid(int x, int y) {
        int boardSize = board.GetSize();
        return x < padding || x > boardSize * gridSideLength + padding ||
                y < padding || y > boardSize * gridSideLength + padding;
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        ChessBoard.Coordinate coordinate = MousePositionToGridCoordinate(e.getX(), e.getY());
        System.out.println("mouse clicked:" + coordinate);
        if (board.CoordinateIsInvalid(coordinate)) {
            return;
        }
        mouseClicked.add(coordinate);
    }

    @Override
    public void mousePressed(MouseEvent e) {

    }

    @Override
    public void mouseReleased(MouseEvent e) {

    }

    @Override
    public void mouseEntered(MouseEvent e) {
        System.out.println("mouseEntered");
    }

    @Override
    public void mouseExited(MouseEvent e) {
        System.out.println("mouseExited");
    }


    @Override
    public void mouseDragged(MouseEvent e) {
        System.out.println("mouseDragged");
    }

    @Override
    public void mouseMoved(MouseEvent e) {
        mouseCoordinate = MousePositionToGridCoordinate(e.getX(), e.getY());
        /*
        System.out.println("mouseMoved:" + e.getX() + "," + e.getY());
        System.out.println("Grid Coordinate:" + mouseCoordinate);
        System.out.println("getWidth:" + getWidth() + ", " + "getHeight:" + getHeight());
        */
        repaint();
    }
}

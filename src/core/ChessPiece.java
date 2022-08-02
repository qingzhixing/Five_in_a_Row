package core;

public enum ChessPiece {
    WHITE("︎︎O"),
    BLACK("G"),
    EMPTY("^");

    private String styleOnConsole = "";

    private ChessPiece(String styleOnConsole) {
        this.styleOnConsole = styleOnConsole;
    }

    public String toString() {
        return styleOnConsole;
    }

    public static ChessPiece RandomPiece() {
        return values()[(int) (Math.random() * values().length)];
    }
}

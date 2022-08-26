package qingzhixing.core.console;

public enum ChessPiece {
    WHITE("︎︎k"),
    BLACK("a"),
    EMPTY("o");

    private String styleOnConsole = "";

    ChessPiece(String styleOnConsole) {
        this.styleOnConsole = styleOnConsole;
    }

    public static ChessPiece RandomPiece() {
        return values()[(int) (Math.random() * values().length)];
    }

    @Override
    public String toString() {
        return styleOnConsole;
    }

    public String toDetailString() {
        return "[" + this.name() + ", " + styleOnConsole + "]";
    }
}

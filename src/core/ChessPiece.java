package core;

public enum ChessPiece {
    WHITE("︎︎k"),
    BLACK("a"),
    EMPTY("o");

    private String styleOnConsole = "";

    private ChessPiece(String styleOnConsole) {
        this.styleOnConsole = styleOnConsole;
    }

    @Override
    public String toString() {
        return styleOnConsole;
    }

    public String toDetailString(){
        return "["+this.name()+", "+styleOnConsole+"]";
    }

    public static ChessPiece RandomPiece() {
        return values()[(int) (Math.random() * values().length)];
    }
}

package qingzhixing.core.console;

import org.jetbrains.annotations.NotNull;

public class Coordinate implements Cloneable {
    public int row;
    public int column;

    public Coordinate(int row, int column) {
        this.row = row;
        this.column = column;
    }

    public boolean equals(@NotNull Coordinate c) {
        return this.row == c.row && this.column == c.column;
    }

    public @NotNull Coordinate Add(@NotNull Coordinate other) {
        return new Coordinate(this.row + other.row, this.column + other.column);
    }

    public @NotNull Coordinate Subtract(@NotNull Coordinate other) {
        return new Coordinate(this.row - other.row, this.column - other.column);
    }

    @Override
    public String toString() {
        return "(" + row + "," + column + ")";
    }
    
    @Override
    public Coordinate clone() {
        return new Coordinate(row, column);
    }
}

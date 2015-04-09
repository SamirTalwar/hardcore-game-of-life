package com.noodlesandwich.gameoflife;

import java.util.Objects;

public final class CellPosition {
    private final int x;
    private final int y;

    public static CellPosition cellAt(int x, int y) {
        return new CellPosition(x, y);
    }

    private CellPosition(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    @Override
    public boolean equals(Object other) {
        if (!(other instanceof CellPosition)) {
            return false;
        }

        CellPosition that = (CellPosition) other;
        return this.x == that.x && this.y == that.y;
    }

    @Override
    public int hashCode() {
        return Objects.hash(x, y);
    }

    @Override
    public String toString() {
        // return String.format("(%d,%d)", x, y);
        return "(" + x + "," + y + ")";
    }
}

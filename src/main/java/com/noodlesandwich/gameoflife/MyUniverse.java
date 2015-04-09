package com.noodlesandwich.gameoflife;

import static com.noodlesandwich.gameoflife.CellPositions.blockAt;
import static com.noodlesandwich.gameoflife.CellPositions.nothing;

public class MyUniverse implements Universe {
    private final CellPositions livingCellPositions;

    public static Universe emptyUniverse() {
        return aUniverseWith(nothing());
    }

    public static Universe aUniverseWith(CellPositions livingCellPositions) {
        return new MyUniverse(livingCellPositions);
    }

    private MyUniverse(CellPositions livingCellPositions) {
        this.livingCellPositions = livingCellPositions;
    }

    @Override
    public Universe tick() {
        if (livingCellPositions.isEmptyOrHasASingleCell()) {
            return emptyUniverse();
        }
        int minX = livingCellPositions.min(CellPosition::getX);
        int minY = livingCellPositions.min(CellPosition::getY);
        return aUniverseWith(blockAt(minX, minY));
    }

    @Override
    public final boolean equals(Object other) {
        if (!(other instanceof MyUniverse)) {
            return false;
        }

        MyUniverse that = (MyUniverse) other;
        return this.livingCellPositions.equals(that.livingCellPositions);
        // note: I like this vs. that
    }

    @Override
    public final int hashCode() {
        return livingCellPositions.hashCode();
    }

    @Override
    public String toString() {
        return getClass().getSimpleName() + ": " + livingCellPositions.toString();
    }
}

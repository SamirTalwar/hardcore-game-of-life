package com.noodlesandwich.gameoflife;

import java.util.Arrays;
import java.util.Comparator;
import java.util.HashSet;
import java.util.Set;
import java.util.function.Function;

import static com.noodlesandwich.gameoflife.CellPosition.cellAt;
import static java.util.stream.Collectors.toSet;
import static java.util.stream.Stream.concat;

public class CellPositions {
    private final Set<CellPosition> livingCellPositions;

    public static CellPositions nothing() {
        return new CellPositions();
    }

    public CellPositions(CellPosition... livingCellPositions) {
        this(new HashSet<>(Arrays.asList(livingCellPositions)));
    }

    private CellPositions(Set<CellPosition> livingCellPositions) {
        this.livingCellPositions = livingCellPositions;
    }

    public CellPositions and(CellPositions other) {
        return new CellPositions(merged(livingCellPositions, other.livingCellPositions));
    }

    private static <T> Set<T> merged(Set<T> a, Set<T> b) {
        // static tells me it does not belong here and can be moved anywhere
        return concat(a.stream(), b.stream()).collect(toSet());
    }

    public boolean isEmptyOrHasASingleCell() {
        return livingCellPositions.size() <= 1;
    }

    public <T extends Comparable<T>> T min(Function<CellPosition, T> mapper) {
        return livingCellPositions.stream().map(mapper).min(Comparator.<T>naturalOrder()).get();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof CellPositions)) {
            return false;
        }

        CellPositions that = (CellPositions) o;
        return this.livingCellPositions.equals(that.livingCellPositions);
    }

    @Override
    public int hashCode() {
        return livingCellPositions.hashCode();
    }

    @Override
    public String toString() {
        return livingCellPositions.toString();
    }

    public static CellPositions singleCellAt(int x, int y) {
        return new CellPositions(
                cellAt(x, y)
        );
    }

    public static CellPositions twoCellsAt(int x, int y) {
        return new CellPositions(
                cellAt(x, y),
                cellAt(x + 1, y)
        );
    }

    public static CellPositions blockAt(int x, int y) {
        return new CellPositions(
                cellAt(x, y),
                cellAt(x + 1, y),
                cellAt(x, y + 1),
                cellAt(x + 1, y + 1)
        );
    }

    public static CellPositions gammaShapeAt(int x, int y) {
        return new CellPositions(
                cellAt(x, y),
                cellAt(x + 1, y),
                cellAt(x, y + 1)
        );
    }

    public static CellPositions reversedGammaShapeAt(int x, int y) {
        return new CellPositions(
                cellAt(x, y + 1),
                cellAt(x + 1, y),
                cellAt(x, y)
        );
    }

    public static CellPositions backwardsLShapeAt(int x, int y) {
        return new CellPositions(
                cellAt(x, y),
                cellAt(x, y + 1),
                cellAt(x - 1, y + 1)
        );
    }

}

package com.noodlesandwich.gameoflife;

import nl.jqno.equalsverifier.EqualsVerifier;
import nl.jqno.equalsverifier.Warning;
import org.junit.Test;

import java.util.*;
import java.util.function.Function;

import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;

public final class UniverseTest {
    // applies rules to the current state of the universe to create the next generation

    // TODO (continue here) last test was vanishing. so what is the next shape?/test case?

    @Test public void
    a_block_is_stable() {
        Universe universe = MyUniverse.aUniverseWith(CellPositions.blockAt(1, 1));

        Universe nextGenerationUniverse = universe.tick();

        assertThat(nextGenerationUniverse, is(equalTo(universe)));
    }

    @Test public void
    a_gamma_shape_becomes_a_block() {
        Universe universe = MyUniverse.aUniverseWith(CellPositions.gammaShapeAt(2, 3));

        Universe nextGenerationUniverse = universe.tick();

        assertThat(nextGenerationUniverse, is(MyUniverse.aUniverseWith(CellPositions.blockAt(2, 3))));
    }

    @Test public void
    a_reversed_gamma_shape_becomes_a_block() {
        Universe universe = MyUniverse.aUniverseWith(CellPositions.reversedGammaShapeAt(2, 3));

        Universe nextGenerationUniverse = universe.tick();

        assertThat(nextGenerationUniverse, is(MyUniverse.aUniverseWith(CellPositions.blockAt(2, 3))));
    }

    @Test public void
    a_backwards_L_shape_becomes_a_block() {
        Universe universe = MyUniverse.aUniverseWith(CellPositions.backwardsLShapeAt(6, 4));

        Universe nextGenerationUniverse = universe.tick();

        assertThat(nextGenerationUniverse, is(MyUniverse.aUniverseWith(CellPositions.blockAt(5, 4))));
    }

    @Test public void
    a_single_cell_vanishes() {
        Universe universe = MyUniverse.aUniverseWith(CellPositions.singleCellAt(6, 9));

        Universe nextGenerationUniverse = universe.tick();

        assertThat(nextGenerationUniverse, is(MyUniverse.emptyUniverse()));
    }

    @Test public void
    universes_are_equal_if_their_cells_are_all_in_the_same_places() {
        EqualsVerifier.forClass(MyUniverse.class)
                .suppress(Warning.NULL_FIELDS)
                .verify();
    }

    static class MyUniverse implements Universe {

        private final CellPositions livingCellPositions;

        public static Universe emptyUniverse() {
            return aUniverseWith(CellPositions.nothing());
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
            return aUniverseWith(CellPositions.blockAt(minX, minY));
        }

        @Override
        public final boolean equals(Object other) {
            if (!(other instanceof MyUniverse)) {
                return false;
            }

            MyUniverse that = (MyUniverse) other;
            // TODO (maybe) do not care for order
            return this.livingCellPositions.equals(that.livingCellPositions);
        }

        @Override
        public final int hashCode() {
            return this.livingCellPositions.hashCode();
        }

        @Override
        public String toString() {
            return getClass().getSimpleName() + ": " + this.livingCellPositions.toString();
        }
    }

    private static final class CellPosition {
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

    private static class CellPositions {
        private final List<CellPosition> livingCellPositions;

        private static CellPositions nothing() {
            return new CellPositions();
        }

        public CellPositions(CellPosition... livingCellPositions) {
            this(Arrays.asList(livingCellPositions));
        }

        private CellPositions(List<CellPosition> livingCellPositions) {
            this.livingCellPositions = livingCellPositions;
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

        public static CellPositions singleCellAt(int x, int y) {
            return new CellPositions(
                    CellPosition.cellAt(x, y)
            );
        }

        public static CellPositions blockAt(int x, int y) {
            return new CellPositions(
                    CellPosition.cellAt(x, y),
                    CellPosition.cellAt(x + 1, y),
                    CellPosition.cellAt(x, y + 1),
                    CellPosition.cellAt(x + 1, y + 1)
            );
        }

        public static CellPositions gammaShapeAt(int x, int y) {
            return new CellPositions(
                    CellPosition.cellAt(x, y),
                    CellPosition.cellAt(x + 1, y),
                    CellPosition.cellAt(x, y + 1)
            );
        }

        public static CellPositions reversedGammaShapeAt(int x, int y) {
            return new CellPositions(
                    CellPosition.cellAt(x, y + 1),
                    CellPosition.cellAt(x + 1, y),
                    CellPosition.cellAt(x, y)
            );
        }

        public static CellPositions backwardsLShapeAt(int x, int y) {
            return new CellPositions(
                    CellPosition.cellAt(x, y),
                    CellPosition.cellAt(x, y + 1),
                    CellPosition.cellAt(x - 1, y + 1)
            );
        }
    }
}

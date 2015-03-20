package com.noodlesandwich.gameoflife;

import nl.jqno.equalsverifier.EqualsVerifier;
import nl.jqno.equalsverifier.Warning;
import org.junit.Test;

import java.util.*;

import static java.util.stream.Collectors.toList;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;

public final class UniverseTest {
    // applies rules to the current state of the universe to create the next generation
    // 1 pixel vanishes
    // other test cases regarding business rules

//    @Test public void
//    one_pixel_vanishes() {
//        assertThat(nextGenerationUniverse, is(empty()));
//    }

    @Test public void
    a_block_is_stable() {
        Universe universe = aUniverseWith(blockAt(1, 1));

        Universe nextGenerationUniverse = universe.tick();

        assertThat(nextGenerationUniverse, is(equalTo(universe)));
    }

    @Test public void
    a_gamma_shape_becomes_a_block() {
        Universe universe = aUniverseWith(gammaShapeAt(2, 3));

        Universe nextGenerationUniverse = universe.tick();

        assertThat(nextGenerationUniverse, is(aUniverseWith(blockAt(2, 3))));
    }

    @Test public void
    a_reversed_gamma_shape_becomes_a_block() {
        Universe universe = aUniverseWith(reverse(gammaShapeAt(2, 3)));

        Universe nextGenerationUniverse = universe.tick();

        assertThat(nextGenerationUniverse, is(aUniverseWith(blockAt(2, 3))));
    }

    @Test public void
    a_backwards_L_shape_becomes_a_block() {
        Universe universe = aUniverseWith(backwardsLShapeAt(6, 4));

        Universe nextGenerationUniverse = universe.tick();

        assertThat(nextGenerationUniverse, is(aUniverseWith(blockAt(5, 4))));
    }

    @Test public void
    universes_are_equal_if_their_cells_are_all_in_the_same_places() {
        EqualsVerifier.forClass(MyUniverse.class)
                .suppress(Warning.NULL_FIELDS)
                .verify();
    }

    private static Universe aUniverseWith(List<CellPosition> livingCellPositions) {
        return new MyUniverse(livingCellPositions);
    }

    private static class MyUniverse implements Universe {
        private final List<CellPosition> livingCellPositions;

        public MyUniverse(List<CellPosition> livingCellPositions) {
            this.livingCellPositions = livingCellPositions;
        }

        @Override
        public Universe tick() {
            List<CellPosition> sortedPositions = livingCellPositions.
                    stream().
                    sorted((a, b) -> {
                        if (a.y != b.y) {
                            return Integer.compare(a.y, b.y);
                        } else {
                            return Integer.compare(a.x, b.x);
                        }
                    }).
                    collect(toList());

            CellPosition firstCell = sortedPositions.get(0);
            CellPosition secondCell = sortedPositions.get(1);
            if (firstCell.y == secondCell.y) {
                return aUniverseWith(blockAt(firstCell.x, firstCell.y));
            }

            return aUniverseWith(blockAt(firstCell.x - 1, firstCell.y));
        }

        @Override
        public final boolean equals(Object other) {
            if (!(other instanceof MyUniverse)) {
                return false;
            }

            MyUniverse that = (MyUniverse) other;
            // TODO do not care for order
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

    private static List<CellPosition> blockAt(int x, int y) {
        return Arrays.asList(
                cellAt(x, y),
                cellAt(x + 1, y),
                cellAt(x, y + 1),
                cellAt(x + 1, y + 1)
        );
    }

    private static List<CellPosition> gammaShapeAt(int x, int y) {
        return Arrays.asList(
                cellAt(x, y),
                cellAt(x + 1, y),
                cellAt(x, y + 1)
        );
    }

    private static List<CellPosition> backwardsLShapeAt(int x, int y) {
        return Arrays.asList(
                cellAt(x, y),
                cellAt(x, y + 1),
                cellAt(x - 1, y + 1)
        );
    }

    private static CellPosition cellAt(int x, int y) {
        return new CellPosition(x, y);
    }

    private static <T> List<T> reverse(List<T> list) {
        Collections.reverse(list);
        return list;
    }

    private static final class CellPosition {
        private final int x;
        private final int y;

        public CellPosition(int x, int y) {
            this.x = x;
            this.y = y;
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
}

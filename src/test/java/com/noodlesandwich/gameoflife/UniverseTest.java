package com.noodlesandwich.gameoflife;

import java.util.Arrays;
import java.util.List;
import org.junit.Ignore;
import org.junit.Test;

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

    @Ignore
    @Test public void
    a_block_is_stable() {
        Universe universe = aUniverseWith(blockAt(1, 1));

        Universe nextGenerationUniverse = universe.tick();

        assertThat(nextGenerationUniverse, is(equalTo(universe)));
    }

    @Ignore
    @Test public void
    an_L_becomes_a_block() {
        Universe universe = aUniverseWith(lShapeAt(2, 3));

        Universe nextGenerationUniverse = universe.tick();

        assertThat(nextGenerationUniverse, is(aUniverseWith(blockAt(2, 3))));
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
            CellPosition firstCell = livingCellPositions.get(0);
            return aUniverseWith(blockAt(firstCell.x, firstCell.y));
        }

        // TODO: equals and hashCode on parameters to make the tests pass.
    }

    private static List<CellPosition> blockAt(int x, int y) {
        return Arrays.asList(
                cellAt(x, y),
                cellAt(x + 1, y),
                cellAt(x, y + 1),
                cellAt(x + 1, y + 1)
        );
    }

    private static List<CellPosition> lShapeAt(int x, int y) {
        return Arrays.asList(
                cellAt(x, y),
                cellAt(x + 1, y),
                cellAt(x, y + 1)
        );
    }

    private static CellPosition cellAt(int x, int y) {
        return new CellPosition(x, y);
    }
    private static class CellPosition {
        private final int x;

        private final int y;
        public CellPosition(int x, int y) {
            this.x = x;
            this.y = y;
        }

    }
}

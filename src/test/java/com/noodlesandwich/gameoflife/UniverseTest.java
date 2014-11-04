package com.noodlesandwich.gameoflife;

import java.util.Arrays;
import java.util.List;
import org.junit.Ignore;
import org.junit.Test;

import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;

@Ignore("Do this next.")
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
    an_L_becomes_a_block() {
        Universe universe = aUniverseWith(lShapeAt(2, 3));

        Universe nextGenerationUniverse = universe.tick();

        assertThat(nextGenerationUniverse, is(aUniverseWith(blockAt(2, 3))));
    }

    private Universe aUniverseWith(List<CellPosition> livingCellPositions) {
        return new Universe() {
            @Override
            public Universe tick() {
                CellPosition firstCell = livingCellPositions.get(0);
                return aUniverseWith(blockAt(firstCell.x, firstCell.y));
            }

            // TODO: equals and hashCode on parameters to make the tests pass.
        };
    }

    private List<CellPosition> blockAt(int x, int y) {
        return Arrays.asList(
                cellAt(x, y),
                cellAt(x + 1, y),
                cellAt(x, y + 1),
                cellAt(x + 1, y + 1)
        );
    }

    private List<CellPosition> lShapeAt(int x, int y) {
        return Arrays.asList(
                cellAt(x, y),
                cellAt(x + 1, y),
                cellAt(x, y + 1)
        );
    }

    private CellPosition cellAt(int x, int y) {
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

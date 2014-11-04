package com.noodlesandwich.gameoflife;

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

    @Test
    public void
    a_block_is_stable() {
        Universe universe = buildUniverseWith(blockAt(1, 1));

        Universe nextGenerationUniverse = universe.tick();

        assertThat(nextGenerationUniverse, is(equalTo(universe)));
    }

    private Universe buildUniverseWith(Object o) {
        return new Universe() {
            @Override
            public Universe tick() {
                return this;
            }
        };
    }

    private Object blockAt(int x, int y) {
        return new Object();
    }
}

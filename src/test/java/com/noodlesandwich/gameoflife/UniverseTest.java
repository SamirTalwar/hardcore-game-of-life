package com.noodlesandwich.gameoflife;

import nl.jqno.equalsverifier.EqualsVerifier;
import nl.jqno.equalsverifier.Warning;
import org.junit.Test;

import static com.noodlesandwich.gameoflife.CellPositions.backwardsLShapeAt;
import static com.noodlesandwich.gameoflife.CellPositions.blockAt;
import static com.noodlesandwich.gameoflife.CellPositions.gammaShapeAt;
import static com.noodlesandwich.gameoflife.CellPositions.reversedGammaShapeAt;
import static com.noodlesandwich.gameoflife.CellPositions.singleCellAt;
import static com.noodlesandwich.gameoflife.MyUniverse.aUniverseWith;
import static com.noodlesandwich.gameoflife.MyUniverse.emptyUniverse;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;

public final class UniverseTest {

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
        Universe universe = aUniverseWith(reversedGammaShapeAt(2, 3));

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
    a_single_cell_vanishes() {
        Universe universe = aUniverseWith(singleCellAt(6, 9));

        Universe nextGenerationUniverse = universe.tick();

        assertThat(nextGenerationUniverse, is(emptyUniverse()));
    }

    @Test public void
    universes_are_equal_if_their_cells_are_all_in_the_same_places() {
        EqualsVerifier.forClass(MyUniverse.class)
                .suppress(Warning.NULL_FIELDS)
                .verify();
    }
}

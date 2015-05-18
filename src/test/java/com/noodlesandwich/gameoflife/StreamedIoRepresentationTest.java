package com.noodlesandwich.gameoflife;

import org.junit.Test;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.StringReader;

import static com.noodlesandwich.gameoflife.CellPositions.backwardsLShapeAt;
import static com.noodlesandwich.gameoflife.CellPositions.blockAt;
import static com.noodlesandwich.gameoflife.CellPositions.singleCellAt;
import static com.noodlesandwich.gameoflife.Joiner.join;
import static com.noodlesandwich.gameoflife.MyUniverse.aUniverseWith;
import static com.noodlesandwich.gameoflife.MyUniverse.emptyUniverse;
import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;

public class StreamedIoRepresentationTest {

    @Test public void
    should_deserialize_empty_universe() throws IOException {
        IoRepresentation ioRepresentation = newStreamedIoRepresentationOf(
                "...",
                "...",
                "...");

        Universe universe = ioRepresentation.deserializeNextUniverse();

        assertThat(universe, is(emptyUniverse()));
    }

    @Test public void
    should_deserialize_a_single_cell_in_a_universe() throws IOException {
        IoRepresentation ioRepresentation = newStreamedIoRepresentationOf(
                "x..",
                "...",
                "...");

        Universe universe = ioRepresentation.deserializeNextUniverse();

        assertThat(universe, is(aUniverseWith(singleCellAt(0, 0))));
    }

    @Test public void
    should_deserialize_block_in_a_universe() throws IOException {
        IoRepresentation ioRepresentation = newStreamedIoRepresentationOf(
                ".xx.",
                ".xx.",
                "....");
        // we make it 4x3 because it does not matter here (so it is the same step size)
        // but it gives better coverage for regression testing

        Universe universe = ioRepresentation.deserializeNextUniverse();

        assertThat(universe, is(aUniverseWith(blockAt(1, 0))));
    }

    @Test public void
    should_deserialize_backwards_L_shape_in_a_universe() throws IOException {
        IoRepresentation ioRepresentation = newStreamedIoRepresentationOf(
                ".x..",
                "xx..",
                "....");

        Universe universe = ioRepresentation.deserializeNextUniverse();

        assertThat(universe, is(aUniverseWith(backwardsLShapeAt(1, 0))));
    }

    private StreamedIoRepresentation newStreamedIoRepresentationOf(String... lines) {
        BufferedReader bufferedReader = new BufferedReader(new StringReader(join(lines)));
        return new StreamedIoRepresentation(bufferedReader);
    }

}

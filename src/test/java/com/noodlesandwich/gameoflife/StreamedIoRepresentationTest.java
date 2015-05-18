package com.noodlesandwich.gameoflife;

import org.junit.Test;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.StringReader;

import static com.noodlesandwich.gameoflife.CellPositions.singleCellAt;
import static com.noodlesandwich.gameoflife.Joiner.join;
import static com.noodlesandwich.gameoflife.MyUniverse.aUniverseWith;
import static com.noodlesandwich.gameoflife.MyUniverse.emptyUniverse;
import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;
import static org.mockito.Mockito.mock;

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


    private StreamedIoRepresentation newStreamedIoRepresentationOf(String... lines) {
        BufferedReader bufferedReader = new BufferedReader(new StringReader(join(lines)));
        return new StreamedIoRepresentation(bufferedReader, dummy(PrintWriter.class));
    }

    private static <T> T dummy(Class<T> type) {
        return mock(type);
    }
}

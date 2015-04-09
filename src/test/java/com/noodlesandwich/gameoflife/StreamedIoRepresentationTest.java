package com.noodlesandwich.gameoflife;

import org.junit.Ignore;
import org.junit.Test;

import java.io.BufferedReader;
import java.io.PrintWriter;
import java.io.StringReader;

import static com.noodlesandwich.gameoflife.Joiner.join;
import static com.noodlesandwich.gameoflife.UniverseTest.emptyUniverse;
import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;
import static org.mockito.Mockito.mock;

public class StreamedIoRepresentationTest {

    @Ignore("next to work in after extracting MyUniverse to Universe top level")
    @Test public void
    should_deserialize_empty_universe() {
        IoRepresentation ioRepresentation = newStreamedIoRepresentationOf(
                "...",
                "...",
                "...");

        Universe universe = ioRepresentation.deserializeNextUniverse();

        assertThat(universe, is(emptyUniverse()));
    }

    private StreamedIoRepresentation newStreamedIoRepresentationOf(String... lines) {
        BufferedReader bufferedReader = new BufferedReader(new StringReader(join(lines)));
        return new StreamedIoRepresentation(bufferedReader, dummy(PrintWriter.class));
    }

    private static <T> T dummy(Class<T> type) {
        return mock(type);
    }
}

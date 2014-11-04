package com.noodlesandwich.gameoflife;

import java.io.BufferedReader;
import java.io.PrintWriter;
import org.junit.Test;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

public final class StreamedUiTest {
    private final BufferedReader anyInput = dummy(BufferedReader.class);
    private final PrintWriter dummyOutput = dummy(PrintWriter.class);
    private final StreamedIoRepresentation streamedIoRepresentation = mock(StreamedIoRepresentation.class);
    private final Universe universe = mock(Universe.class);

    private <T> T dummy(Class<T> type) {
        return mock(type);
    }

    @Test public void
    creates_universe_and_ticks_it() {
        StreamedUi ui = new StreamedUi(anyInput, dummyOutput, streamedIoRepresentation);
        when(streamedIoRepresentation.deserialize(anyInput)).thenReturn(universe);

        ui.tick();

        verify(universe).tick();
    }

    // test return output
}

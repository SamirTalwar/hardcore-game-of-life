package com.noodlesandwich.gameoflife;

import org.junit.Test;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

public final class UiTest {
    private final IoRepresentation ioRepresentation = mock(IoRepresentation.class);
    private final Universe universe = mock(Universe.class);

    @Test public void
    creates_universe_and_ticks_it() {
        Ui ui = new Ui(ioRepresentation);
        when(ioRepresentation.deserializeNextUniverse()).thenReturn(universe);

        ui.tick();

        verify(universe).tick();

        // TODO next rename MyUniverse to Universe
        // this test breaks because of Mockito now after we renamed it - think about it!

    }

    // TODO test return output
}

package com.noodlesandwich.gameoflife;

import java.io.BufferedReader;
import java.io.PrintWriter;

public final class StreamedUi {

    private final BufferedReader in;
    private final PrintWriter out;
    private final StreamedIoRepresentation streamedIoRepresentation;

    public StreamedUi(BufferedReader in, PrintWriter out, StreamedIoRepresentation streamedIoRepresentation) {
        this.in = in;
        this.out = out;
        this.streamedIoRepresentation = streamedIoRepresentation;
    }

    public void tick() {
        Universe universe = streamedIoRepresentation.deserialize(in);
        universe.tick();
    }
}

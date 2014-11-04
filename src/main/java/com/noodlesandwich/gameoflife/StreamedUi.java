package com.noodlesandwich.gameoflife;

import java.io.BufferedReader;
import java.io.PrintWriter;

public final class StreamedUi {

    private final BufferedReader in;
    private final PrintWriter out;
    private final Deserializer deserializer;

    public StreamedUi(BufferedReader in, PrintWriter out, Deserializer deserializer) {
        this.in = in;
        this.out = out;
        this.deserializer = deserializer;
    }

    public void tick() {
        Universe universe = deserializer.xxx(in);
        universe.tick();
    }
}

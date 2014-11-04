package com.noodlesandwich.gameoflife;

import java.io.BufferedReader;
import java.io.PrintWriter;

public final class StreamedUi {

    private final BufferedReader in;
    private final PrintWriter out;

    public StreamedUi(BufferedReader in, PrintWriter out) {
        this.in = in;
        this.out = out;
    }

    public void tick() {
        throw new UnsupportedOperationException();
    }
}

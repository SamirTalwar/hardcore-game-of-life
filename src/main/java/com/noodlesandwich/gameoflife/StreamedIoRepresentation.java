package com.noodlesandwich.gameoflife;

import java.io.BufferedReader;
import java.io.PrintWriter;

public class StreamedIoRepresentation implements IoRepresentation {
    public StreamedIoRepresentation(BufferedReader bufferedReader, PrintWriter printWriter) {
    }

    @Override
    public Universe deserialize() {
        throw new UnsupportedOperationException();
    }
}

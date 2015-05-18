package com.noodlesandwich.gameoflife;

import java.io.BufferedReader;
import java.io.PrintWriter;

import static com.noodlesandwich.gameoflife.MyUniverse.emptyUniverse;

public class StreamedIoRepresentation implements IoRepresentation {
    public StreamedIoRepresentation(BufferedReader bufferedReader, PrintWriter printWriter) {
    }

    @Override
    public Universe deserializeNextUniverse() {
        return emptyUniverse();
    }
}

package com.noodlesandwich.gameoflife;

import java.io.BufferedReader;
import java.io.PrintWriter;

public final class Ui {

    private final IoRepresentation ioRepresentation;

    public Ui(IoRepresentation ioRepresentation) {
        this.ioRepresentation = ioRepresentation;
    }

    public void tick() {
        Universe universe = ioRepresentation.deserialize();
        universe.tick();
    }
}

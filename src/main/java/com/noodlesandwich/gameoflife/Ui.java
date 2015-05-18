package com.noodlesandwich.gameoflife;

import java.io.IOException;

public final class Ui {

    private final IoRepresentation ioRepresentation;

    public Ui(IoRepresentation ioRepresentation) {
        this.ioRepresentation = ioRepresentation;
    }

    // TODO rename this to something that implies that it works with I/O
    public void tick() throws IOException {
        Universe universe = ioRepresentation.deserializeNextUniverse();
        universe.tick();
        // we are losing ourselves in names here. deserialize, tick, all is wrong ;-)
    }
}

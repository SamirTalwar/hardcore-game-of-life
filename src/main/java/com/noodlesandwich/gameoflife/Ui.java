package com.noodlesandwich.gameoflife;

public final class Ui {

    private final IoRepresentation ioRepresentation;

    public Ui(IoRepresentation ioRepresentation) {
        this.ioRepresentation = ioRepresentation;
    }

    public void tick() {
        Universe universe = ioRepresentation.deserializeNextUniverse();
        universe.tick();
        // we are losing ourselves in names here. deserialize, tick, all is wrong ;-)
    }
}

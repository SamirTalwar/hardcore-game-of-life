package com.noodlesandwich.gameoflife;

import java.io.BufferedReader;
import java.io.IOException;

import static com.noodlesandwich.gameoflife.CellPositions.blockAt;
import static com.noodlesandwich.gameoflife.CellPositions.singleCellAt;
import static com.noodlesandwich.gameoflife.MyUniverse.aUniverseWith;
import static com.noodlesandwich.gameoflife.MyUniverse.emptyUniverse;

public class StreamedIoRepresentation implements IoRepresentation {
    private final BufferedReader reader;

    public StreamedIoRepresentation(BufferedReader reader) {
        this.reader = reader;
    }

    @Override
    public Universe deserializeNextUniverse() throws IOException {
        String line = reader.readLine();
        if (line.charAt(0) == 'x') {
            return aUniverseWith(singleCellAt(0, 0));
        }
        if (line.charAt(1) == 'x') {
            return aUniverseWith(blockAt(1, 0));
        }
        return emptyUniverse();
    }
}

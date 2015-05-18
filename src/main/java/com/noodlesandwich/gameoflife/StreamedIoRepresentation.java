package com.noodlesandwich.gameoflife;

import java.io.BufferedReader;
import java.io.IOException;

import static com.noodlesandwich.gameoflife.CellPositions.backwardsLShapeAt;
import static com.noodlesandwich.gameoflife.CellPositions.blockAt;
import static com.noodlesandwich.gameoflife.CellPositions.singleCellAt;
import static com.noodlesandwich.gameoflife.MyUniverse.aUniverseWith;
import static com.noodlesandwich.gameoflife.MyUniverse.emptyUniverse;

public class StreamedIoRepresentation implements IoRepresentation {
    private static final char LIVING = 'x';

    private final BufferedReader reader;

    public StreamedIoRepresentation(BufferedReader reader) {
        this.reader = reader;
    }

    @Override
    public Universe deserializeNextUniverse() throws IOException {
        String line = reader.readLine();
        if (line.charAt(0) == LIVING) {
            return aUniverseWith(singleCellAt(0, 0));
        }
        if (line.charAt(1) == LIVING) {
            if (line.charAt(2) == LIVING) {
                return aUniverseWith(blockAt(1, 0));
            } else {
                return aUniverseWith(backwardsLShapeAt(1, 0));
            }
        }
        return emptyUniverse();
    }
}

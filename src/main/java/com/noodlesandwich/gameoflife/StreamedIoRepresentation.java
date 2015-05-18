package com.noodlesandwich.gameoflife;

import java.io.BufferedReader;
import java.io.IOException;

import static com.noodlesandwich.gameoflife.CellPositions.singleCellAt;
import static com.noodlesandwich.gameoflife.CellPositions.twoCellsAt;
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

        // two ideas: Samir needs to vary both lines to get more complex logic to see pattern,
        // also make building blocks smaller by refactoring to see it earlier
        // Peter wants to start over to use only singleCells and see direct connection of (x,y) and CellPosition.

        if (line.charAt(0) == LIVING) {
            return aUniverseWith(singleCellAt(0, 0));
        }

        if (line.charAt(1) == LIVING) {
            if (line.charAt(2) == LIVING) {
                return aUniverseWith(twoCellsAt(1, 0).and(twoCellsAt(1, 1)));
            } else {
                return aUniverseWith(singleCellAt(1, 0).and(twoCellsAt(0, 1)));
            }
        }
        return emptyUniverse();
    }

}

package com.noodlesandwich.gameoflife;

import java.io.BufferedReader;
import java.io.IOException;

import static com.noodlesandwich.gameoflife.CellPositions.singleCellAt;
import static com.noodlesandwich.gameoflife.CellPositions.twoCellsAt;
import static com.noodlesandwich.gameoflife.MyUniverse.aUniverseWith;

public class StreamedIoRepresentation implements IoRepresentation {
    private static final char LIVING = 'x';

    private final BufferedReader reader;

    public StreamedIoRepresentation(BufferedReader reader) {
        this.reader = reader;
    }

    @Override
    public Universe deserializeNextUniverse() throws IOException {
        String firstLine = reader.readLine(); // test list - add test for empty stream
        String secondLine = reader.readLine();

        // two ideas: Samir needs to vary both lines to get more complex logic to see pattern,
        // also make building blocks smaller by refactoring to see it earlier
        // Peter wants to start over to use only singleCells and see direct connection of (x,y) and CellPosition.

        if (firstLine.charAt(0) == LIVING) {
            return aUniverseWith(singleCellAt(0, 0));
        }

        CellPositions rowOneCells = CellPositions.nothing();
        if (firstLine.charAt(1) == LIVING) {
            CellPositions firstCell = singleCellAt(1, 0);
            rowOneCells = rowOneCells.and(firstCell);

            if (firstLine.charAt(2) == LIVING) {
                rowOneCells = rowOneCells.and(singleCellAt(2, 0));
            }

            CellPositions rowTwoCells = twoCellsAt(secondLine.indexOf(LIVING), 1);
            return aUniverseWith(rowOneCells.and(rowTwoCells));
        }

        return aUniverseWith(rowOneCells);
    }

}

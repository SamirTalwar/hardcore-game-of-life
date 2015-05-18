package com.noodlesandwich.gameoflife;

import java.io.BufferedReader;
import java.io.IOException;
import java.util.stream.IntStream;

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
        // TODO Peter wants to start over to use only singleCells and see direct connection of (x,y) and CellPosition.

        if (firstLine.charAt(0) == LIVING) {
            return aUniverseWith(singleCellAt(0, 0));
        }

        CellPositions cells = IntStream.range(0, firstLine.length())
                .filter(i -> firstLine.charAt(i) == LIVING)
                .mapToObj(i -> singleCellAt(i, 0))
                .reduce(CellPositions.nothing(), CellPositions::and);

        if (firstLine.charAt(1) == LIVING) { // ugly, wrong tests
            CellPositions rowTwoCells = twoCellsAt(secondLine.indexOf(LIVING), 1);
            cells = cells.and(rowTwoCells);
        }

        return aUniverseWith(cells);
    }

}

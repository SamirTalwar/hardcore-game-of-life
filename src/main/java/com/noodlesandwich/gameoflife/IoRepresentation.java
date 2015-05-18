package com.noodlesandwich.gameoflife;

import java.io.IOException;

public interface IoRepresentation {
    Universe deserializeNextUniverse() throws IOException;
}

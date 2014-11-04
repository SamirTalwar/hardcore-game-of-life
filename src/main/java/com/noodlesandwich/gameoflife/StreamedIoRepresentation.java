package com.noodlesandwich.gameoflife;

import java.io.BufferedReader;

public interface StreamedIoRepresentation {
    Universe deserialize(BufferedReader in);
}

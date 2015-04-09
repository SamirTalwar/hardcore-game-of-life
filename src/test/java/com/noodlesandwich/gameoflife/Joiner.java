package com.noodlesandwich.gameoflife;

import java.util.stream.Stream;

import static java.util.stream.Collectors.joining;

public final class Joiner {
    public static String join(String... lines) {
        return Stream.of(lines)
                .map(line -> line + System.lineSeparator())
                .collect(joining());
    }
}

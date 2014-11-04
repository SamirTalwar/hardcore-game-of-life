package com.noodlesandwich.gameoflife;

import java.io.BufferedReader;
import java.io.PrintWriter;
import java.io.Reader;
import java.io.StringReader;
import java.io.StringWriter;
import java.util.stream.Stream;
import org.hamcrest.Matcher;
import org.junit.Ignore;
import org.junit.Test;

import static java.util.stream.Collectors.joining;
import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;

@Ignore("Pending")
public final class GameOfLifeGuidanceTest {
    private Reader commandLineInput;
    private final StringWriter commandLineOutput = new StringWriter();

    @Test public void
    a_blinker_blinks() {
        givenTheInputIs(
                ".*.",
                ".*.",
                ".*."
        );

        whenTheGameTicks();

        assertThat(theOutput(), looksLike(
                "...",
                "***",
                "..."
        ));
    }

    private void givenTheInputIs(String... lines) {
        commandLineInput = new StringReader(join(lines));
    }

    private void whenTheGameTicks() {
        StreamedUi ui = createUiWith(new BufferedReader(commandLineInput), new PrintWriter(commandLineOutput));
        ui.tick();
    }

    private StreamedUi createUiWith(BufferedReader in, PrintWriter out) {
        return new StreamedUi(in, out, null);
    }

    private String theOutput() {
        return commandLineOutput.toString();
    }

    private Matcher<String> looksLike(String... lines) {
        return is(join(lines));
    }

    // TODO: Move elsewhere.
    private String join(String[] lines) {
        return Stream.of(lines)
                .map(line -> line + System.lineSeparator())
                .collect(joining());
    }
}

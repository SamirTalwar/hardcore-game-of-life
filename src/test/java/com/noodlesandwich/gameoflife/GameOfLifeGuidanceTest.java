package com.noodlesandwich.gameoflife;

import org.hamcrest.Matcher;
import org.junit.Ignore;
import org.junit.Test;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.Reader;
import java.io.StringReader;
import java.io.StringWriter;

import static com.noodlesandwich.gameoflife.Joiner.join;
import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;

@Ignore("Pending")
public final class GameOfLifeGuidanceTest {
    private Reader commandLineInput;
    private final StringWriter commandLineOutput = new StringWriter();

    @Test public void
    a_block_is_static() throws IOException {
        String[] universe = {
            "........",
            "........",
            "........",
            "........",
            "........",
            "....**..",
            "....**..",
            "........"
        };

        givenTheInputIs(universe);

        whenTheGameTicks();

        assertThat(theOutput(), looksLike(universe));
    }

    @Test public void
    a_blinker_blinks() throws IOException {
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

    private void whenTheGameTicks() throws IOException {
        Ui ui = createUi();
        ui.tick();
    }

    private Ui createUi() {
        return new Ui(new StreamedIoRepresentation(new BufferedReader(commandLineInput), new PrintWriter(commandLineOutput)));
    }

    private String theOutput() {
        return commandLineOutput.toString();
    }

    private Matcher<String> looksLike(String... lines) {
        return is(join(lines));
    }
}

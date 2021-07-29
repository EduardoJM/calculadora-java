package io.github.eduardojm.calculator.core;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class LexerTest {
    @Test
    void testLexerEofMustBeReturnTrueOnlyIfStreamEnded() {
        var input = new CharStream("exa");
        var lexer = new Lexer(input);
        assertFalse(lexer.eof());
        input.next();
        assertFalse(lexer.eof());
        input.next();
        assertFalse(lexer.eof());
        input.next();
        assertTrue(lexer.eof());
    }
}

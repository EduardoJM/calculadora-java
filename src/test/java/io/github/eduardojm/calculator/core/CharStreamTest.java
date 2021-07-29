package io.github.eduardojm.calculator.core;


import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class CharStreamTest {
    static CharStream input;

    @BeforeEach
    public void setup() {
        input = new CharStream("example");
    }

    @DisplayName("The next method must be change the position of the CharStream")
    @Test
    void testNextMethodMustBeChangePosition() {
        assertEquals(input.getPosition(), 0);
        input.next();
        assertEquals(input.getPosition(), 1);
    }

    @DisplayName("The next method must be return the next character of the input string")
    @Test
    void testNextMethodMustBeReturnNextCharacter() {
        assertEquals(input.next(), 'e');
        assertEquals(input.next(), 'x');
        assertEquals(input.next(), 'a');
        assertEquals(input.next(), 'm');
        assertEquals(input.next(), 'p');
        assertEquals(input.next(), 'l');
        assertEquals(input.next(), 'e');
    }

    @DisplayName("The peek method must be not change the position of the CharStream")
    @Test
    void testPeekMethodMustBeNotChangeThePosition() {
        assertEquals(input.getPosition(), 0);
        var ch = input.peek();
        assertEquals(input.getPosition(), 0);
        assertEquals(ch, input.peek());
    }

    @DisplayName("The eof method must be return true only if the string was ended")
    @Test
    void testEofMethodMustBeReturnTrueOnlyWhenInputEnded() {
        assertFalse(input.eof());
        assertEquals(input.next(), 'e');
        assertFalse(input.eof());
        assertEquals(input.next(), 'x');
        assertFalse(input.eof());
        assertEquals(input.next(), 'a');
        assertFalse(input.eof());
        assertEquals(input.next(), 'm');
        assertFalse(input.eof());
        assertEquals(input.next(), 'p');
        assertFalse(input.eof());
        assertEquals(input.next(), 'l');
        assertFalse(input.eof());
        assertEquals(input.next(), 'e');
        assertTrue(input.eof());
    }
}

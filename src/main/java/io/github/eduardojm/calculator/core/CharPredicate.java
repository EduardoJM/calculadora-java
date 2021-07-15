package io.github.eduardojm.calculator.core;

/**
 * A functional interface that defines a character predicate for character match testing.
 */
@FunctionalInterface
public interface CharPredicate {
    /**
     * Test if the character matches determinate parameters.
     * @param value the character to test.
     * @return true if the character matches the parameters, false otherwise.
     */
    public boolean test(char value);
}

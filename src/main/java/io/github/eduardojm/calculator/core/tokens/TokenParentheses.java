package io.github.eduardojm.calculator.core.tokens;

import io.github.eduardojm.calculator.core.Token;

/**
 * A class that implements a parentheses token.
 */
public final class TokenParentheses extends Token {
    /**
     * The parentheses character.
     */
    protected char value;

    /**
     * Creates a new instance of the TokenParentheses.
     * @param tokenValue The parentheses character.
     */
    public TokenParentheses(char tokenValue) {
        super("parentheses");
        this.value = tokenValue;
    }

    /**
     * Gets the parentheses character.
     * @return Returns the parentheses character.
     */
    public char getValue() {
        return this.value;
    }
}


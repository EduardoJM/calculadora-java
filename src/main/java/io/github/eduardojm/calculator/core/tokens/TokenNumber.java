package io.github.eduardojm.calculator.core.tokens;

import io.github.eduardojm.calculator.core.Token;

/**
 * A class that implements a number token.
 */
public final class TokenNumber extends Token {
    /**
     * The number value.
     */
    protected float value;

    /**
     * Creates a new instance of the TokenNumber.
     * @param tokenValue The number value.
     */
    public TokenNumber(float tokenValue) {
        super("number");
        this.value = tokenValue;
    }

    /**
     * Gets the number value.
     * @return Returns the number value.
     */
    public float getValue() {
        return this.value;
    }
}

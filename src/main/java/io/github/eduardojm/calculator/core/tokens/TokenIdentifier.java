package io.github.eduardojm.calculator.core.tokens;

import io.github.eduardojm.calculator.core.Token;

/**
 * A class that implements a identifier token.
 */
public final class TokenIdentifier extends Token {
    /**
     * The identifier value.
     */
    protected String value;

    /**
     * Creates a new instance of the TokenIdentifier.
     * @param tokenValue the identifier value.
     */
    public TokenIdentifier(String tokenValue) {
        super("identifier");
        this.value = tokenValue;
    }

    /**
     * Gets the identifier value.
     * @return Returns the identifier value.
     */
    public String getValue() {
        return this.value;
    }
}

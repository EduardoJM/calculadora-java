package io.github.eduardojm.calculator.core;

/**
 * A abstract class that implements the base of the token.
 */
public abstract class Token {
    /**
     * The token type.
     */
    protected String type;

    /**
     * Creates a new instance of the Token.
     * @param tokenType The token type.
     */
    public Token(String tokenType) {
        this.type = tokenType;
    }

    /**
     * Gets the token type.
     * @return Returns the token type.
     */
    public String getType() {
        return this.type;
    }
}

package io.github.eduardojm.calculator.core.tokens;

import io.github.eduardojm.calculator.core.Token;

/**
 * A class that implements a function token.
 */
public final class TokenFunction extends Token {
    /**
     * The function name.
     */
    protected String name;
    /**
     * The function argument.
     */
    protected Token value;

    /**
     * Creates a new instance of TokenFunction.
     * @param tokenFunction The function name.
     * @param tokenValue The function argument.
     */
    public TokenFunction(String tokenFunction, Token tokenValue) {
        super("function");
        this.name = tokenFunction;
        this.value = tokenValue;
    }

    /**
     * Gets the function argument token.
     * @return Returns the function argument token.
     */
    public Token getValue() {
        return this.value;
    }

    /**
     * Gets the function name.
     * @return Returns the function name.
     */
    public String getName() { return this.name; }
}

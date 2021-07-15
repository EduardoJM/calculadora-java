package io.github.eduardojm.calculator.core.tokens;

import io.github.eduardojm.calculator.core.Token;

/**
 * A class that implements a operator token.
 */
public final class TokenOperator extends Token {
    /**
     * The operator character.
     */
    protected char value;

    /**
     * Creates a new instance of TokenOperator.
     * @param tokenValue The operator character.
     */
    public TokenOperator(char tokenValue) {
        super("operator");
        this.value = tokenValue;
    }

    /**
     * Get the operator character.
     * @return Returns the operator character.
     */
    public char getValue() {
        return this.value;
    }

    /**
     * Get the operator precedence.
     * @return Returns the precedence of this operator.
     */
    public int getPrecedence() {
        if (this.value == '+' || this.value == '-') {
            return 1;
        } else if (this.value == '*' || this.value == '/') {
            return 5;
        } else if (this.value == '^') {
            return 10;
        }
        return 0;
    }
}

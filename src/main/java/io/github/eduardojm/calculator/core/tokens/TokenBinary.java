package io.github.eduardojm.calculator.core.tokens;

import io.github.eduardojm.calculator.core.Token;

/**
 * A class that implements the binary operation token.
 */
public final class TokenBinary extends Token {
    /**
     * The left operator token.
     */
    private final Token left;
    /**
     * The right operator token.
     */
    private final Token right;
    /**
     * The operator character.
     */
    private final char operator;

    /**
     * Creates a new instance of the TokenBinary.
     * @param tokenLeft The left operator token.
     * @param tokenRight The right operator token.
     * @param tokenOperator The operator character.
     */
    public TokenBinary(Token tokenLeft, Token tokenRight, char tokenOperator) {
        super("binary");
        this.left = tokenLeft;
        this.right = tokenRight;
        this.operator = tokenOperator;
    }

    /**
     * Gets the left operator token.
     * @return Returns the left operator token.
     */
    public Token getLeft() {
        return left;
    }

    /**
     * Gets the right operator token.
     * @return Returns the right operator token.
     */
    public Token getRight() {
        return right;
    }

    /**
     * Gets the operator character.
     * @return Returns the operator character.
     */
    public char getOperator() {
        return operator;
    }
}


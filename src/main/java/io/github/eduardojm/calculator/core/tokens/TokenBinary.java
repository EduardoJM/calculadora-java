package io.github.eduardojm.calculator.core.tokens;

import io.github.eduardojm.calculator.core.Token;

public final class TokenBinary extends Token {
    private Token left;
    private Token right;
    private char operator;

    public TokenBinary(Token tokenLeft, Token tokenRight, char tokenOperator) {
        super("binary");
        this.left = tokenLeft;
        this.right = tokenRight;
        this.operator = tokenOperator;
    }

    public Token getLeft() {
        return left;
    }

    public Token getRight() {
        return right;
    }

    public char getOperator() {
        return operator;
    }
}


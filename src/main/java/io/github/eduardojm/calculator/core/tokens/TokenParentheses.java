package io.github.eduardojm.calculator.core.tokens;

import io.github.eduardojm.calculator.core.Token;

public final class TokenParentheses extends Token {
    protected char value;

    public TokenParentheses(char tokenValue) {
        super("parentheses");
        this.value = tokenValue;
    }

    public char getValue() {
        return this.value;
    }
}


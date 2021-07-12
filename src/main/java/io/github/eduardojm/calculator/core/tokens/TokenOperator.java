package io.github.eduardojm.calculator.core.tokens;

import io.github.eduardojm.calculator.core.Token;

public final class TokenOperator extends Token {
    protected char value;

    public TokenOperator(char tokenValue) {
        super("operator");
        this.value = tokenValue;
    }

    public char getValue() {
        return this.value;
    }

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

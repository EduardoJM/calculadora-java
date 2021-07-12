package io.github.eduardojm.calculator.core.tokens;

import io.github.eduardojm.calculator.core.Token;

public final class TokenNumber extends Token {
    protected float value;

    public TokenNumber(float tokenValue) {
        super("number");
        this.value = tokenValue;
    }

    public float getValue() {
        return this.value;
    }
}

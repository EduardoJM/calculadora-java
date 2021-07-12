package io.github.eduardojm.calculator.core.tokens;

import io.github.eduardojm.calculator.core.Token;

public final class TokenIdentifier extends Token {
    protected String value;

    public TokenIdentifier(String tokenValue) {
        super("identifier");
        this.value = tokenValue;
    }

    public String getValue() {
        return this.value;
    }
}

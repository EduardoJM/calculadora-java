package io.github.eduardojm.calculator.core.tokens;

import io.github.eduardojm.calculator.core.Token;
import java.util.List;

public final class TokenFunction extends Token {
    private String name;
    private Token value;

    public TokenFunction(String tokenFunction, Token tokenValue) {
        super("function");
        this.name = tokenFunction;
        this.value = tokenValue;
    }

    public Token getValue() {
        return this.value;
    }

    public String getName() { return this.name; }
}

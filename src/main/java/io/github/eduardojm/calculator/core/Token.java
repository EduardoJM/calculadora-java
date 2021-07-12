package io.github.eduardojm.calculator.core;

public abstract class Token {
    protected String type;

    public Token(String tokenType) {
        this.type = tokenType;
    }

    public String getType() {
        return this.type;
    }
}

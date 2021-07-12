package io.github.eduardojm.calculator.core.tokens;

import io.github.eduardojm.calculator.core.Token;
import java.util.List;

public final class TokenExpression extends Token {
    private List<Token> value;

    public TokenExpression(List<Token> tokenValue) {
        super("expression");
        this.value = tokenValue;
    }

    public List<Token> getValue() {
        return this.value;
    }
}

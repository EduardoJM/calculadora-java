package io.github.eduardojm.calculator.core;

import io.github.eduardojm.calculator.core.tokens.TokenBinary;
import io.github.eduardojm.calculator.core.tokens.TokenNumber;

public class Evaluator {
    private final Token expression;

    public Evaluator(Token expr) {
        this.expression = expr;
    }

    public float evaluate() throws Exception {
        return eval(this.expression);
    }

    private static float applyOperator(char operator, float a, float b) throws Exception {
        switch (operator) {
            case '+': return a + b;
            case '-': return a - b;
            case '*': return a * b;
            case '^': return (float)Math.pow(a, b);
            case '/': return a / b;
        }
        throw new Exception("Unknown operator: " + operator);
    }

    private static float eval(Token token) throws Exception {
        switch (token.getType()) {
            case "binary" -> {
                TokenBinary operation = (TokenBinary) token;
                return applyOperator(operation.getOperator(), eval(operation.getLeft()), eval(operation.getRight()));
            }
            case "number" -> {
                TokenNumber num = (TokenNumber) token;
                return num.getValue();
            }
        }
        throw new Exception("Unknown token: " + token.getType());
    }
}

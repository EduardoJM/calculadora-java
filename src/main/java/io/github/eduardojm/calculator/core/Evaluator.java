package io.github.eduardojm.calculator.core;

import io.github.eduardojm.calculator.core.tokens.TokenBinary;
import io.github.eduardojm.calculator.core.tokens.TokenFunction;
import io.github.eduardojm.calculator.core.tokens.TokenNumber;

import java.util.HashMap;
import java.util.Map;

public class Evaluator {
    private final Token expression;
    private Map<String, InternFunction> functions;

    public Evaluator(Token expr) {
        this.expression = expr;
        this.functions = new HashMap<>();
        this.functions.put("ln", value -> (float)Math.log(value));
        this.functions.put("log", value -> (float)Math.log10(value));
    }

    public float evaluate() throws Exception {
        return eval(this.expression);
    }

    private float applyOperator(char operator, float a, float b) throws Exception {
        switch (operator) {
            case '+': return a + b;
            case '-': return a - b;
            case '*': return a * b;
            case '^': return (float)Math.pow(a, b);
            case '/': return a / b;
        }
        throw new Exception("Unknown operator: " + operator);
    }

    private float eval(Token token) throws Exception {
        switch (token.getType()) {
            case "binary" -> {
                TokenBinary operation = (TokenBinary) token;
                return this.applyOperator(operation.getOperator(), this.eval(operation.getLeft()), this.eval(operation.getRight()));
            }
            case "number" -> {
                TokenNumber num = (TokenNumber) token;
                return num.getValue();
            }
            case "function" -> {
                TokenFunction func = (TokenFunction) token;
                if (!this.functions.containsKey(func.getName())) {
                    throw new Exception("Unknown function: " + func.getName());
                }
                return functions.get(func.getName()).eval(this.eval(func.getValue()));
            }
        }
        throw new Exception("Unknown token: " + token.getType());
    }
}

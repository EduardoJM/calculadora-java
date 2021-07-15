package io.github.eduardojm.calculator.core;

import io.github.eduardojm.calculator.core.tokens.TokenBinary;
import io.github.eduardojm.calculator.core.tokens.TokenFunction;
import io.github.eduardojm.calculator.core.tokens.TokenIdentifier;
import io.github.eduardojm.calculator.core.tokens.TokenNumber;

import java.util.HashMap;
import java.util.Map;

public class Evaluator {
    private final Token expression;
    private final Map<String, InternFunction> functions;
    private final Map<String, Float> constants;

    public Evaluator(Token expr) {
        this.expression = expr;
        this.functions = new HashMap<>();
        this.functions.put("ln", value -> (float)Math.log(value));
        this.functions.put("log", value -> (float)Math.log10(value));
        this.constants = new HashMap<>();
        this.constants.put("pi", (float)Math.PI);
        this.constants.put("e", (float)Math.E);
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
            case "identifier" -> {
                TokenIdentifier identifier = (TokenIdentifier) token;
                if (!this.constants.containsKey(identifier.getValue())) {
                    throw new Exception("Unknown constant: " + identifier.getValue());
                }
                return constants.get(identifier.getValue());
            }
        }
        throw new Exception("Unknown token: " + token.getType());
    }
}

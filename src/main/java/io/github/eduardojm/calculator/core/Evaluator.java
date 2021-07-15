package io.github.eduardojm.calculator.core;

import io.github.eduardojm.calculator.core.tokens.TokenBinary;
import io.github.eduardojm.calculator.core.tokens.TokenFunction;
import io.github.eduardojm.calculator.core.tokens.TokenIdentifier;
import io.github.eduardojm.calculator.core.tokens.TokenNumber;

import java.util.HashMap;
import java.util.Map;

/**
 * A class that implements an Evaluator for the calculator.
 */
public class Evaluator {
    /**
     * The Token to evaluate.
     */
    private final Token expression;
    /**
     * Intern functions that we can evaluate.
     */
    private final Map<String, InternFunction> functions;
    /**
     * The constants that we can evaluate.
     */
    private final Map<String, Float> constants;

    /**
     * Creates a new instance of the Evaluator.
     * @param expr The token to evaluate.
     */
    public Evaluator(Token expr) {
        this.expression = expr;
        this.functions = new HashMap<>();
        this.functions.put("ln", value -> (float)Math.log(value));
        this.functions.put("log", value -> (float)Math.log10(value));
        this.constants = new HashMap<>();
        this.constants.put("pi", (float)Math.PI);
        this.constants.put("e", (float)Math.E);
    }

    /**
     * Evaluate the token to a float number.
     * @return The float value evaluated from the token that this class is created with it..
     * @throws Exception This method throws a Exception in the possibilities: if the token is a function and the function
     * is not known. If the token is a identifier and the constant is not known. If the token is not known. If a unknown
     * operator token is trying to be used to apply operators.
     */
    public float evaluate() throws Exception {
        return eval(this.expression);
    }

    /**
     * Apply a operator with two numbers (left and right) of the binary operator.
     * @param operator The operator character.
     * @param a The left number.
     * @param b The right number.
     * @return Returns the result of the binary operation between the two numbers.
     * @throws Exception Throws a Exception if the operator is not known, i.e. if the operator is not implemented
     * by the Evaluator.
     */
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

    /**
     * Evaluate a token to float.
     * @param token The token to evaluate.
     * @return A float value evaluated from this token.
     * @throws Exception The method throws a Exception in three possibilities: if the token is a function and the function
     * is not known. If the token is a identifier and the constant is not known. If the token is not known.
     */
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

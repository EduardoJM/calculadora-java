package io.github.eduardojm.calculator.core;

/**
 * Functional Interface that defines a function used in the Evaluator.
 */
@FunctionalInterface
public interface InternFunction {
    /**
     * Evaluate the function to a float argument.
     * @param number The function argument.
     * @return Returns the function result.
     */
    public float eval(float number);
}

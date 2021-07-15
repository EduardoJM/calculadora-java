package io.github.eduardojm.calculator.core;

import io.github.eduardojm.calculator.core.tokens.TokenIdentifier;
import io.github.eduardojm.calculator.core.tokens.TokenNumber;
import io.github.eduardojm.calculator.core.tokens.TokenOperator;
import io.github.eduardojm.calculator.core.tokens.TokenParentheses;

import java.util.Optional;

/**
 * A class that implements a Lexer for the calculator.
 */
public class Lexer {
    /**
     * The input character stream.
     */
    private final CharStream stream;
    /**
     * The current peek token (Can be null).
     */
    private Token current;

    /**
     * Creates a new instance of the Lexer.
     * @param input The input character stream.
     */
    public Lexer(CharStream input) {
        this.stream = input;
        this.current = null;
    }

    /**
     * Peek a token. Returns the next token and not skip it position.
     * @return Returns the next token.
     * @throws Exception This method can throws a Exception if can't parse the input.
     */
    public Optional<Token> peek() throws Exception {
        if (this.current != null) {
            return Optional.of(this.current);
        }
        this.current = this.readNext().orElse(null);
        return Optional.ofNullable(this.current);
    }

    /**
     * Get the next token and skip it position.
     * @return Returns the next token.
     * @throws Exception This method can throws a Exception if can't parse the input.
     */
    public Optional<Token> next() throws Exception {
        Token tok = this.current;
        this.current = null;
        if (tok != null) {
            return Optional.of(tok);
        }
        return this.readNext();
    }

    /**
     * Gets a value indicating if is the end of the stream.
     * @return Returns a boolean value indicating if is the end of the stream.
     */
    public boolean eof() {
        return this.stream.eof();
    }

    /**
     * Parse a number token.
     * @return Returns the parsed TokenNumber.
     */
    private TokenNumber parseNumber() {
        String value = this.readWhile(this.isDigit);
        float parsedNumber = Float.parseFloat(value);
        return new TokenNumber(parsedNumber);
    }

    /**
     * Read the next token.
     * @return Returns the read next token or Optional.empty() if is end of the stream.
     * @throws Exception Throws a Exception if the Lexer or Character Stream can't parse the input.
     */
    private Optional<Token> readNext() throws Exception {
        this.readWhile(this.isWhiteSpace);
        if (this.stream.eof()) {
            return Optional.empty();
        }
        var ch = this.stream.peek();
        if (isDigitStart.test(ch)) {
            return Optional.of(this.parseNumber());
        } else if (this.isOperator.test(ch)) {
            return Optional.of(new TokenOperator(this.stream.next()));
        } else if (this.isParentheses.test(ch)) {
            return Optional.of(new TokenParentheses(this.stream.next()));
        } else if (this.isIdentifierStart.test(ch)) {
            return Optional.of(new TokenIdentifier(this.readWhile(this.isIdentifier)));
        }
        throw new Exception("Invalid token: " + ch);
    }

    /**
     * A CharPredicate that indicate if a character is the white space.
     */
    private final CharPredicate isWhiteSpace = ch -> ch == ' ';
    /**
     * A CharPredicate that indicates if is the start of a number.
     */
    private final CharPredicate isDigitStart = Character::isDigit;
    /**
     * A CharPredicate that indicates if is a part of a digit number.
     */
    private final CharPredicate isDigit = ch -> Character.isDigit(ch) || ch == '.';
    /**
     * A CharPredicate that indicates if the character is a operator.
     */
    private final CharPredicate isOperator = ch -> ch == '-' || ch == '+' || ch == '*' || ch == '/' || ch == '^';
    /**
     * A CharPredicate that indicates if the character is a parentheses.
     */
    private final CharPredicate isParentheses = ch -> ch == '(' || ch == ')';
    /**
     * A CharPredicate that indicates if the character is the start of a identifier.
     */
    private final CharPredicate isIdentifierStart = ch -> String.valueOf(ch).matches("[A-z_]");
    /**
     * A CharPredicate that indicates if the character is a part of a identifier.
     */
    private final CharPredicate isIdentifier = ch -> isIdentifierStart.test(ch) || isDigitStart.test(ch);

    /**
     * Read from CharStream while a determinate condition.
     * @param condition The condition to continue reading.
     * @return Returns the read string.
     */
    private String readWhile(CharPredicate condition) {
        StringBuilder str = new StringBuilder();
        while (!this.stream.eof() && condition.test(this.stream.peek())) {
            str.append(this.stream.next());
        }
        return str.toString();
    }
}

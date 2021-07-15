package io.github.eduardojm.calculator.core;

import io.github.eduardojm.calculator.core.tokens.TokenIdentifier;
import io.github.eduardojm.calculator.core.tokens.TokenNumber;
import io.github.eduardojm.calculator.core.tokens.TokenOperator;
import io.github.eduardojm.calculator.core.tokens.TokenParentheses;

import java.util.Optional;

public class Lexer {
    private final CharStream stream;
    private Token current;

    public Lexer(CharStream input) {
        this.stream = input;
        this.current = null;
    }

    public Optional<Token> peek() throws Exception {
        if (this.current != null) {
            return Optional.of(this.current);
        }
        this.current = this.readNext().orElse(null);
        return Optional.ofNullable(this.current);
    }

    public Optional<Token> next() throws Exception {
        Token tok = this.current;
        this.current = null;
        if (tok != null) {
            return Optional.of(tok);
        }
        return this.readNext();
    }

    public boolean eof() {
        return this.stream.eof();
    }

    private TokenNumber parseNumber() {
        String value = this.readWhile(this.isDigit);
        float parsedNumber = Float.parseFloat(value);
        return new TokenNumber(parsedNumber);
    }

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

    private final CharPredicate isWhiteSpace = ch -> ch == ' ';
    private final CharPredicate isDigitStart = Character::isDigit;
    private final CharPredicate isDigit = ch -> Character.isDigit(ch) || ch == '.';
    private final CharPredicate isOperator = ch -> ch == '-' || ch == '+' || ch == '*' || ch == '/' || ch == '^';
    private final CharPredicate isParentheses = ch -> ch == '(' || ch == ')';
    private final CharPredicate isIdentifierStart = ch -> String.valueOf(ch).matches("[A-z_]");
    private final CharPredicate isIdentifier = ch -> isIdentifierStart.test(ch) || isDigitStart.test(ch);

    private String readWhile(CharPredicate condition) {
        StringBuilder str = new StringBuilder();
        while (!this.stream.eof() && condition.test(this.stream.peek())) {
            str.append(this.stream.next());
        }
        return str.toString();
    }
}

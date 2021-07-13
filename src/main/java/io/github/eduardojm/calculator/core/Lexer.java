package io.github.eduardojm.calculator.core;

import io.github.eduardojm.calculator.core.tokens.TokenIdentifier;
import io.github.eduardojm.calculator.core.tokens.TokenNumber;
import io.github.eduardojm.calculator.core.tokens.TokenOperator;
import io.github.eduardojm.calculator.core.tokens.TokenParentheses;

public class Lexer {
    private final CharStream stream;
    private Token current;

    public Lexer(CharStream input) {
        this.stream = input;
        this.current = null;
    }

    public Token peek() throws Exception {
        if (this.current != null) {
            return this.current;
        }
        this.current = this.readNext();
        return this.current;
    }

    public Token next() throws Exception {
        Token tok = this.current;
        this.current = null;
        if (tok != null) {
            return tok;
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

    private Token readNext() throws Exception {
        this.readWhile(this.isWhiteSpace);
        if (this.stream.eof()) {
            return null;
        }
        var ch = this.stream.peek();
        if (isDigitStart.test(ch)) {
            return this.parseNumber();
        } else if (this.isOperator.test(ch)) {
            return new TokenOperator(this.stream.next());
        } else if (this.isParentheses.test(ch)) {
            return new TokenParentheses(this.stream.next());
        } else if (this.isIdentifierStart.test(ch)) {
            return new TokenIdentifier(this.readWhile(this.isIdentifier));
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

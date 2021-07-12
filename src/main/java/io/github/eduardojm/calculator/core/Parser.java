package io.github.eduardojm.calculator.core;

import io.github.eduardojm.calculator.core.tokens.TokenBinary;
import io.github.eduardojm.calculator.core.tokens.TokenExpression;
import io.github.eduardojm.calculator.core.tokens.TokenOperator;
import io.github.eduardojm.calculator.core.tokens.TokenParentheses;

import java.util.ArrayList;
import java.util.List;

public class Parser {
    private Lexer lexer;

    public Parser(Lexer input) {
        this.lexer = input;
    }

    public Token parse() throws Exception {
        if (!this.lexer.eof()) {
            return this.parseExpression();
        }
        return null;
    }

    private Token parseExpression() throws Exception {
        return this.parseMaybeBinary(this.parseAtom(), 0);
    }

    private Token parseMaybeBinary(Token left, int myPrecedence) throws Exception {
        var peekToken = this.lexer.peek();
        if (peekToken == null) {
            return left;
        }
        if (peekToken.getType().equals("operator")) {
            TokenOperator operator = (TokenOperator)peekToken;
            var hisPrecedence = operator.getPrecedence();
            if (hisPrecedence > myPrecedence) {
                this.lexer.next();
                var right = this.parseMaybeBinary(this.parseAtom(), hisPrecedence);
                var binary = new TokenBinary(left, right, operator.getValue());
                return this.parseMaybeBinary(binary, myPrecedence);
            }
        }
        return left;
    };

    private Token parseAtom() throws Exception {
        var peekToken = this.lexer.peek();
        if (peekToken.getType().equals("parentheses")) {
            TokenParentheses parentheses = (TokenParentheses) peekToken;
            if (parentheses.getValue() == '(') {
                var expr = this.parseExpression();
                var anotherPeek = this.lexer.peek();
                if (anotherPeek.getType().equals("parentheses")) {
                    TokenParentheses other = (TokenParentheses) anotherPeek;
                    if (other.getValue() != ')') {
                        throw new Exception("Unknown token here: " + other.getValue());
                    }
                    this.lexer.next();
                } else {
                    throw new Exception("Unknown token type here: " + anotherPeek.getType());
                }
                return expr;
            }
        } else if (peekToken.getType().equals("number")) {
            return this.lexer.next();
        }
        throw new Exception("Unknown token type: " + peekToken.getType());
    };
}

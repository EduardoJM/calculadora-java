package io.github.eduardojm.calculator.core;

import io.github.eduardojm.calculator.core.tokens.*;

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

    private Token parseParenthesesEnclosed() throws Exception {
        this.lexer.next(); // skip parentheses
        var expr = this.parseExpression();
        var anotherPeek = this.lexer.peek();
        if (anotherPeek.getType().equals("parentheses")) {
            TokenParentheses other = (TokenParentheses) anotherPeek;
            if (other.getValue() != ')') {
                // not match ending parentheses
                throw new Exception("Invalid Token Value: " + other.getValue());
            }
            this.lexer.next(); // skip closing parentheses
            return expr;
        }
        throw new Exception("Invalid Token Type: " + anotherPeek.getType());
    }

    private Token parseAtom() throws Exception {
        var peekToken = this.lexer.peek();
        if (peekToken.getType().equals("parentheses")) {
            TokenParentheses parentheses = (TokenParentheses) peekToken;
            if (parentheses.getValue() == '(') {
                return this.parseParenthesesEnclosed();
            }
        } else if (peekToken.getType().equals("identifier")) {
            TokenIdentifier name = (TokenIdentifier)lexer.next();
            peekToken = this.lexer.peek();
            if (peekToken.getType().equals("parentheses")) {
                var expr = this.parseParenthesesEnclosed();
                return new TokenFunction(name.getValue(), expr);
            }
        } else if (peekToken.getType().equals("number")) {
            return this.lexer.next();
        }
        throw new Exception("Unknown token type: " + peekToken.getType());
    };
}

package io.github.eduardojm.calculator.core;

import io.github.eduardojm.calculator.core.tokens.*;

import java.util.Optional;

public class Parser {
    private final Lexer lexer;

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
        var atom = this.parseAtom();
        if (atom.isEmpty()) {
            throw new Exception("Unknown expression to parse.");
        }
        return this.parseMaybeBinary(atom.get(), 0);
    }

    private Token parseMaybeBinary(Token left, int myPrecedence) throws Exception {
        var peek = this.lexer.peek();
        if (peek.isEmpty()) {
            // if the next token is not present, return the left.
            return left;
        }
        var peekToken = peek.get();
        if (peekToken.getType().equals("operator")) {
            TokenOperator operator = (TokenOperator)peekToken;
            var hisPrecedence = operator.getPrecedence();
            if (hisPrecedence > myPrecedence) {
                this.lexer.next();
                var atom = this.parseAtom();
                if (atom.isEmpty()) {
                    return left;
                }
                var right = this.parseMaybeBinary(atom.get(), hisPrecedence);
                var binary = new TokenBinary(left, right, operator.getValue());
                return this.parseMaybeBinary(binary, myPrecedence);
            }
        }
        return left;
    };

    private Token parseParenthesesEnclosed() throws Exception {
        this.lexer.next(); // skip parentheses
        var expr = this.parseExpression();
        var otherSideToken = this.lexer.peek();
        if (otherSideToken.isEmpty()) {
            throw new Exception("Opened parentheses must be closed.");
        }
        var anotherPeek = otherSideToken.get();
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

    private Optional<Token> parseAtom() throws Exception {
        var peek = this.lexer.peek();
        if (peek.isEmpty()) {
            return Optional.empty();
        }
        var peekToken = peek.get();
        switch (peekToken.getType()) {
            case "parentheses" -> {
                TokenParentheses parentheses = (TokenParentheses) peekToken;
                if (parentheses.getValue() == '(') {
                    return Optional.of(this.parseParenthesesEnclosed());
                }
            }
            case "identifier" -> {
                lexer.next(); // skip the peeked identifier

                TokenIdentifier name = (TokenIdentifier) peekToken;
                peek = this.lexer.peek();
                if (peek.isPresent()) {
                    peekToken = peek.get();
                    if (peekToken.getType().equals("parentheses")) {
                        var parenthesesToken = (TokenParentheses) peekToken;
                        if (parenthesesToken.getValue() == '(') {
                            var expr = this.parseParenthesesEnclosed();
                            return Optional.of(new TokenFunction(name.getValue(), expr));
                        } else {
                            // can be closing parentheses.
                            return Optional.of(name);
                        }
                    } else {
                        return Optional.of(name);
                    }
                } else {
                    return Optional.of(name);
                }
            }
            case "number" -> {
                return this.lexer.next();
            }
        }
        throw new Exception("Unknown token type: " + peekToken.getType());
    };
}

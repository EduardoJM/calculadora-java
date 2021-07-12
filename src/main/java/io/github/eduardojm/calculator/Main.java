package io.github.eduardojm.calculator;

import io.github.eduardojm.calculator.core.*;
import io.github.eduardojm.calculator.core.tokens.TokenExpression;

public class Main {
    public static void main(String[] args) {

        var testing = "10 + 20 * 2";
        var stream = new CharStream(testing);
        var lexer = new Lexer(stream);
        var parser = new Parser(lexer);

        try {
            Token expr = parser.parse();
            Evaluator evaluator = new Evaluator(expr);
            System.out.println(evaluator.evaluate());
        } catch (Exception e) {
            e.printStackTrace();
        }

        /*
        while(!lexer.eof()) {
            try {
                var token = lexer.next();
                System.out.println(token.getType());
            } catch (Exception e) {
                e.printStackTrace();
                break;
            }
        }
        */
    }
}

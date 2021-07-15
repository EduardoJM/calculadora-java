package io.github.eduardojm.calculator;

import io.github.eduardojm.calculator.core.*;

import java.io.BufferedReader;
import java.io.InputStreamReader;

public class Main {
    private static void evaluate(String str) {
        var stream = new CharStream(str);
        var lexer = new Lexer(stream);
        var parser = new Parser(lexer);

        try {
            Token expr = parser.parse();
            Evaluator evaluator = new Evaluator(expr);
            System.out.println(evaluator.evaluate());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        var buffer = new BufferedReader(new InputStreamReader(System.in));

        while (true) {
            System.out.println("Digite uma expressão a ser cálculada (ou \\q para sair):");
            try {
                String expr = buffer.readLine();
                if (expr.startsWith("\\q")) {
                    break;
                }
                evaluate(expr);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}

package org.example;

import java.util.List;
import java.util.Stack;

public class Evaluator {
    public static double evaluate(List<String> postfix) {
        if (postfix == null || postfix.isEmpty()) {
            throw new IllegalArgumentException("Expression is empty");
        }

        Stack<Double> stack = new Stack<>();

        for (String token : postfix) {
            if (token.matches("-?\\d+")) { // E. g. 3 + 4 * 2 / ( 1 - 5 )  |  3 4 2 * 1 5 - / +
                stack.push(Double.parseDouble(token));
            } else if (token.equals("u-")) {
                stack.push(-stack.pop());
            } else {
                if (stack.size() < 2) {
                    throw new IllegalArgumentException("Not enough operands for operator: " + token);
                }
                double b = stack.pop();
                double a = stack.pop();
                switch (token) {
                    case "+":
                        stack.push(a + b);
                        break;
                    case "-":
                        stack.push(a - b);
                        break;
                    case "*":
                        stack.push(a * b);
                        break;
                    case "/":
                        if (b == 0.0) throw new ArithmeticException("Division by zero");
                        stack.push(a / b);
                        break;
                    default:
                        throw new UnsupportedOperationException("Unsupported operator: " + token);
                }
            }
        }
        if (stack.size() != 1) {
            throw new IllegalArgumentException("Too many operands or not enough operators");
        }
        return stack.pop();
    }
}

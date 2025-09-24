package org.example;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ShuntingYard {
    public static List<String> infixToPostfix(String expression) {
        Stack<String> stack = new Stack<>();
        List<String> output = new ArrayList<>();
        List<String> tokens = tokenize(expression);
        System.out.println(tokens);
        String prevToken = null;

        for (String token : tokens) {
            if (token == null || token.trim().isEmpty()) {
                throw new IllegalArgumentException("Empty or blank token encountered");
            }
            if (isNumber(token)) {
                output.add(token);
            } else if (isOperator(token)) {
                if (token.equals("-") && (prevToken == null || isOperator(prevToken) || prevToken.equals("("))) {
                    stack.push("u-");
                } else {
                    while (!stack.isEmpty() && precedence(stack.peek()) >= precedence(token)) {
                        output.add(stack.pop());
                    }
                    stack.push(token);
                }
            } else if (token.equals("(")) {
                stack.push(token);
            } else if (token.equals(")")) {
                while (!stack.peek().equals("(")) {
                    output.add(stack.pop());
                }
                stack.pop(); // remove "("
            } else {
                throw new IllegalArgumentException("Invalid token: " + token);
            }

            prevToken = token;
        }

        while (!stack.isEmpty()) {
            output.add(stack.pop());
        }

        return output;
    }

    private static boolean isNumber(String token) {
        return token.matches("\\d+");
    }

    private static boolean isOperator(String token) {
        return "+-*/".contains(token) || token.equals("u-");
    }

    private static int precedence(String op) {
        return switch (op) {
            case "u-" -> 3; // унарный минус выше всех
            case "^" -> 2;
            case "*", "/" -> 1;
            case "+", "-" -> 0;
            default -> -1;
        };
    }

    public static List<String> tokenize(String expression) {
        if (expression.contains(".") || expression.contains(",")) {
            throw new IllegalArgumentException("Floating point numbers are not supported");
        }
        List<String> tokens = new ArrayList<>();
        String cleaned = expression.replaceAll("\\s+", "");
        Matcher matcher = Pattern.compile("\\d+|[+\\-*/^()]").matcher(cleaned);
        while (matcher.find()) {
            tokens.add(matcher.group());
        }
        return tokens;
    }
}

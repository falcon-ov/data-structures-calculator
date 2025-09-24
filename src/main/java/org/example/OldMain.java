package org.example;

import java.util.Scanner;
import java.util.Stack;

public class OldMain {
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        System.out.print("input: ");

//        String inputLine = input.nextLine();

        String inputLine = "(11 + 18) * 20 - 2 ";
        System.out.println(inputLine);
        char[] charExpression = inputLine.toCharArray();

        Stack<Integer> nums = new Stack<>();
        Stack<Character> ops = new Stack<>();

        boolean isLastDigit = false;
        for (char c : charExpression) {
            if(c == ' ') continue;
            if (Character.isDigit(c) && isLastDigit) {
                nums.add(Integer.valueOf((Integer.toString(nums.pop())+Character.toString(c))));
                continue;
            }
            if (Character.isDigit(c)) {
                isLastDigit = true;
                nums.add(Character.getNumericValue(c));
                continue;
            }
            if (!Character.isDigit(c)) {
                isLastDigit = false;
                ops.add(c);
            }
        }

        int idx = 0;
        while (nums.size() != 1) {
            if (ops.contains(')')) {
                idx = ops.indexOf('(');
                ops.remove(idx);
                while (ops.get(idx) != ')') {
                    int idxA = idx;
                    while (ops.get(idxA) != ')') {
                        if (ops.get(idxA) == '*' || ops.get(idxA) == '/') {
                            preCalc(ops, nums, idxA);
                            idxA--;
                        }
                        idxA++;
                    }
                    preCalc(ops,nums,idx);
                }
                ops.remove(idx);
            }
            int idxA = idx;
            while (idxA < ops.size()) {
                if (ops.get(idxA) == '*' || ops.get(idxA) == '/') {
                    preCalc(ops, nums, idxA);
                    idxA--;
                }
                idxA++;
            }
            preCalc(ops, nums, idx);
        }
        System.out.println("output: "+nums.pop());
    }

    public static void preCalc(Stack<Character> ops, Stack<Integer> nums, int idx){
        char op = ops.remove(idx);
        int left = nums.remove(idx);
        int right = nums.remove(idx);
        nums.add(idx, calc(left, right, op));
    }

    public static int calc(int left, int right, char op) {
        return switch (op) {
            case '+' -> left + right;
            case '-' -> left - right;
            case '*' -> left * right;
            case '/' -> {
                if (right == 0) throw new ArithmeticException("Division by zero");
                yield left / right;
            }
            default -> throw new IllegalArgumentException("Unknown operator: " + op);
        };
    }
}
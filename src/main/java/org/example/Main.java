package org.example;

import java.util.List;
import java.util.Scanner;

/*
Task:
Use the stack to evaluate arithmetic expressions.
The program will be tested with compound expressions with multiple operators and parentheses.
For simplicity assume that the operands are integers, and the operators are of four types: +, -, *, /.
Catch all the errors that you can find.

1. Недостаточно операндов для оператора
2. Лишние операнды (не хватает операторов)
3. Деление на ноль
4. Неверный токен (не число и не оператор)
5. Неподдерживаемый оператор
6. Пустой список
7. Неправильный формат числа
8. Пробелы, пустые строки, null

 */

public class Main {
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        System.out.print("input: ");
//        String expression = input.nextLine();
        String expression = "3 + 4 * 2 / ( -1 - 5 )";
        System.out.println(expression);
        List<String> postfix = ShuntingYard.infixToPostfix(expression);
        System.out.println(postfix);
        double result = Evaluator.evaluate(postfix);
        System.out.println("Result: " + result);
    }
}

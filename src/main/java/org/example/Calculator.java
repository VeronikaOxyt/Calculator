package org.example;
import java.util.Stack;
import java.util.function.DoubleBinaryOperator;

public class Calculator {

    public double decide (String expression) throws IllegalArgumentException {
        Validator validator = new Validator();
        var redoneExpression = addZeroForMinus(expression);

        if (!validator.checkForCorrectSymbols(expression))
            throw new IllegalArgumentException("Выражение содержит недопустимые символы.");

        if (!validator.checkForBalancedBrackets(expression))
            throw new IllegalArgumentException("Передано некорректное выражение, нарушен контроль скобок.");

        return reversePolishNotationToResult(expToReversePolishNotation(redoneExpression));
    }
    private String expToReversePolishNotation(String expression) {
        StringBuilder stringOfNumbers = new StringBuilder();
        Stack<Character> stack = new Stack<>();
        int priority;
        for (int i = 0; i < expression.length(); i++) {
            priority = getPriority((expression.charAt(i)));

            switch (priority) {
                case 0:
                    stringOfNumbers.append(expression.charAt(i));
                    break;
                case 1:
                    stack.push(expression.charAt(i));
                    break;
                case -1:
                    stringOfNumbers.append(' ');
                    while (getPriority(stack.peek()) != 1) {
                        stringOfNumbers.append(stack.pop());
                        stringOfNumbers.append(' ');
                    }
                    stack.pop();
                    break;
                default:
                    stringOfNumbers.append(' ');
                    while (!stack.empty()) {
                        if (getPriority(stack.peek()) >= priority) {
                            stringOfNumbers.append(stack.pop());
                            stringOfNumbers.append(' ');
                        }
                        else break;
                    }
                    stack.push(expression.charAt(i));
                    break;
            }
            
        }
        while (!stack.empty()) {
            stringOfNumbers.append(stack.pop());
        }
        return stringOfNumbers.toString();
    }

    private double reversePolishNotationToResult(String notation) {
        DoubleBinaryOperator add = (a, b) -> a + b;
        DoubleBinaryOperator subtract = (a, b) -> a - b;
        DoubleBinaryOperator multiply = (a, b) -> a * b;
        DoubleBinaryOperator divide = (a, b) -> a / b;
        StringBuilder operand;
        Stack<Double> stack = new Stack<>();

        for (int i = 0; i < notation.length(); i++) {
            if (notation.charAt(i) == ' ') {
                continue;
            }
            if (getPriority(notation.charAt(i)) == 0) {
                operand = new StringBuilder();
                while (notation.charAt(i) != ' ' && getPriority(notation.charAt(i)) == 0) {
                    operand.append(notation.charAt(i++));
                    if (i == notation.length()) break;
                }
                stack.push(Double.parseDouble(operand.toString()));
            }
            if (getPriority(notation.charAt(i)) > 1) {
                double b = stack.pop();
                double a = stack.pop();

                var operator = notation.charAt(i);
                switch (operator) {
                    case '+':
                        stack.push(add.applyAsDouble(a, b));
                        break;
                    case '-':
                        stack.push(subtract.applyAsDouble(a, b));
                        break;
                    case '*':
                        stack.push(multiply.applyAsDouble(a, b));
                        break;
                    case '/':
                        stack.push(divide.applyAsDouble(a, b));
                        break;
                }
            }
        }
        return stack.pop();
    }

    private int getPriority(char operator) {
        int result = 0;
        switch (operator) {
            case '*':
                result = 3;
                break;
            case '/':
                result = 3;
                break;
            case '+':
                result = 2;
                break;
            case '-':
                result = 2;
                break;
            case '(':
                result = 1;
                break;
            case ')':
                result = -1;
                break;
        }
        return result;
    }

    private String addZeroForMinus(String expression) {
        StringBuilder redoneExpression = new StringBuilder();
        for (int token = 0; token < expression.length(); token++) {
            char symbol = expression.charAt(token);
            if (symbol == '-' && (token == 0 || expression.charAt(token - 1) == '(')) {
                redoneExpression.append('0');
            }
            redoneExpression.append(symbol);
        }
        return redoneExpression.toString();
    }
}

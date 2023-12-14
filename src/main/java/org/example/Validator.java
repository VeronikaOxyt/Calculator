package org.example;

import java.util.*;

public class Validator {
    private final static List<Character> validСharacters = List.of('0', '1', '2', '3', '4',
            '5', '6', '7', '8', '9', '+', '-', '*', '/', '(', ')');

    public boolean checkForCorrectSymbols(String string) {
        for (char symbol : string.toCharArray()) {
            if (!validСharacters.contains(symbol)) {
                return false;
            }
        }
        return true;
    }
    public boolean checkForBalancedBrackets(String string) {
        StringBuilder inputStringBuilder = new StringBuilder();
        Map<Character, Character> bracketsMap = new HashMap<>();
        Deque<Character> bracketsDeque = new LinkedList<>();

        for (int i = 0; i < string.length(); i++) {
            if (string.charAt(i) == '(' || string.charAt(i) == ')') {
                inputStringBuilder.append(string.charAt(i));
            }
        }
        bracketsMap.put(')', '(');

        for (char symbol : inputStringBuilder.toString().toCharArray()) {
            if (bracketsMap.containsValue(symbol)) {
                bracketsDeque.push(symbol);
            } else if (bracketsMap.containsKey(symbol) && (bracketsDeque.isEmpty() || !
                            Objects.equals(bracketsDeque.pop(), bracketsMap.get(symbol)))) {
                    return false;
            }
        }
        return bracketsDeque.isEmpty();
    }
}

package org.example;

public class Main {
    public static void main(String[] args) {
        String string = "3-2+5*(6-3*(4-2))";
        System.out.println(string + " = " + new Calculator().initCalculate(string));
    }
}
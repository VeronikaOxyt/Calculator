package org.example;

import static java.lang.System.*;

public class Main {

    public static void main(String[] args) {
        String string = "-3+5*((6+6)/3-2)";
        out.println(string + " = " + new Calculator().initCalculate(string));
    }
}
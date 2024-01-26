package org.example;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
class CalculatorTest {
    Calculator calculator;
    @BeforeEach
    void init() {
        calculator = new Calculator();
    }

    @ParameterizedTest
    @CsvSource({
            "0-5+2*3, -5+2*3",
            "5-2*(0-3+5), 5-2*(-3+5)",
            "5-2*(3+5), 5-2*(3+5)"
    })
    @DisplayName("Testing add a zero to the minus sign method")
    void addZeroForMinusTest(String expected, String input) {
        Assertions.assertEquals(expected, calculator.addZeroForMinus(input),
                () -> "Should return "+ expected + " but "
                + calculator.addZeroForMinus(input));
    }

    @ParameterizedTest
    @CsvSource({
            "1 2 +  4 * 3+, (1+2)*4+3",
            "0 3 - 5 6 6 +  3 / 2 - *+, 0-3+5*((6+6)/3-2)"
    })
    @DisplayName("Testing expression to reverse Polish Notation method")
    void expToReversePolishNotationTest(String expected, String input) {
        Assertions.assertEquals(expected, calculator.expToReversePolishNotation(input),
                () -> "Should return "+ expected + " but "
                        + calculator.expToReversePolishNotation(input));
    }

    @ParameterizedTest
    @CsvSource({
            "15, 1 2 +  4 * 3+",
            "7, 0 3 - 5 6 6 +  3 / 2 - *+"
    })
    @DisplayName("Testing reverse Polish Notation to result of the expression method")
    void reversePolishNotationToResultTest(double expected, String input) {
        Assertions.assertEquals(expected, calculator.reversePolishNotationToResult(input),
                () -> "Should return "+ expected + " but "
                        + calculator.reversePolishNotationToResult(input));
    }

    @Test
    @DisplayName("Testing calculation of the expression method")
    void initCalculateTest() {

        assertAll(
                () -> assertThrows(IllegalArgumentException.class,
                        () -> calculator.initCalculate("-6+5*@"),
                        "an expression with invalid characters should throw exception"),
                () -> assertThrows(IllegalArgumentException.class,
                        () -> calculator.initCalculate("((1+2)*(4+3"),
                        "an expression with with a broken balance of brackets should throw exception"),
                () -> assertThrows(IllegalArgumentException.class,
                        () -> calculator.initCalculate("((1+2)*(4+3)"),
                        "an expression with with a broken balance of brackets should throw exception"),
                () -> assertEquals(15.0,
                        calculator.initCalculate("(1+2)*4+3"),
                        () -> "Should return "+ 15.0 +" but "
                                + calculator.initCalculate("(1+2)*4+3"))
        );
    }
}
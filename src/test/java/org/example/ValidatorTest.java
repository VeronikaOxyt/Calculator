package org.example;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import static org.junit.jupiter.api.Assertions.*;

class ValidatorTest {

    Validator validator;

    @BeforeEach
    void init() {
        validator = new Validator();
    }

    @ParameterizedTest
    @CsvSource({
            "true, 10-5+2*3",
            "false, 5-2*(3+#5)"
    })
    @DisplayName("Testing check expression for correct symbols method")
    void checkForCorrectSymbolsTest(boolean expected, String input) {
        Assertions.assertEquals(expected, validator.checkForCorrectSymbols(input),
                () -> "Should return " + expected + " but "
                        + validator.checkForCorrectSymbols(input));
    }

    @ParameterizedTest
    @CsvSource({
            "true, ((10-4)*(5+2))*3",
            "false, (5-2*(3+5)",
            "false, 5-2)*(3+5"
    })
    @DisplayName("Testing check expression for correct balance brackets method")
    void checkForBalancedBracketsTest(boolean expected, String input) {
        Assertions.assertEquals(expected, validator.checkForBalancedBrackets(input),
                () -> "Should return " + expected + " but "
                        + validator.checkForBalancedBrackets(input));
    }

}
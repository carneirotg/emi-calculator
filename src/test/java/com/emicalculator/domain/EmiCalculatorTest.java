package com.emicalculator.domain;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.stream.Stream;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import com.emicalculator.domain.exception.InvalidInputForEmiCalculationException;

class EmiCalculatorTest {

    private final EmiCalculator testObject = new EmiCalculator();

    @ParameterizedTest()
    @MethodSource("provideInputForEmiCalculation")
    void whenValidEntry_thenCalculateEmi(long loanValue, double interestRate, int loanTerm, double expected) throws InvalidInputForEmiCalculationException {

        var emi = testObject.calculate(loanValue, interestRate, loanTerm);
        assertEquals(expected, emi);

    }

    @ParameterizedTest()
    @MethodSource("provideInvalidInputForEmiCalculation")
    void whenInvalidEntry_thenThrowException(long loanValue, double interestRate, int loanTerm) {
        Assertions.assertThrows(InvalidInputForEmiCalculationException.class, () -> {
            testObject.calculate(loanValue, interestRate, loanTerm);
        });
    }

    public static Stream<Arguments> provideInvalidInputForEmiCalculation() {
        return Stream.of(
            Arguments.of(1000L, -10.0, 2),
            Arguments.of(-1000L, 10.0, 2),
            Arguments.of(1000L, 10.0, -40));
    }

    private static Stream<Arguments> provideInputForEmiCalculation() {
        return Stream.of(
            Arguments.of(100000L, 7.0, 2, 4477.26),
            Arguments.of(5000000L, 9.0, 20, 44986.3),
            Arguments.of(500000, 37.5, 15, 15686.66));
    }

}
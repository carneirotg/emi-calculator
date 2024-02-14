package com.emicalculator.domain;

import java.math.BigDecimal;
import java.math.RoundingMode;

import org.springframework.stereotype.Service;

import com.emicalculator.domain.exception.InvalidInputForEmiCalculationException;

@Service
public class EmiCalculator {

    /**
     * Calculates the EMI based on the loanValue, yearly interest rate and loan term in years. It will convert the loan term to months
     * to be able to apply the formula.
     *
     * @param loanValue positive number greater than 0 representing the amount to lend.
     * @param yearlyInterestRate percentage greater than 0 and lesser than 100 representing the interest rate to apply.
     * @param loanTerm positive number greater than 0 representing the amount in years to calculate the monthly payment
     *
     * @return EMI calculated based on the inputs
     */
    public Double calculate(long loanValue, double yearlyInterestRate, int loanTerm) throws InvalidInputForEmiCalculationException {
        //Formula: EMI = P x R x (1+R)^N / [(1+R)^N-1]
        // where, “P” is the loan amount, “N” is tenure in months, and “R” is the monthly interest rate.

        if (loanValue <= 0 || yearlyInterestRate < 0 || loanTerm <= 0) {
            throw new InvalidInputForEmiCalculationException("Input is invalid for calculation");
        }

        double monthlyInterestRate = yearlyInterestRate / 12 / 100;
        int loanTermInMonths = loanTerm * 12;

        BigDecimal scaledEmi = new BigDecimal(loanValue * monthlyInterestRate * Math.pow(1 + monthlyInterestRate, loanTermInMonths) /
            (Math.pow(1 + monthlyInterestRate, loanTermInMonths) - 1)).setScale(2, RoundingMode.HALF_UP);

        return scaledEmi.doubleValue();
    }
}

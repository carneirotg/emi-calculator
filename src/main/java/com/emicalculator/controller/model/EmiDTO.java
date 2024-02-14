package com.emicalculator.controller.model;


import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.PositiveOrZero;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class EmiDTO {

    @Positive(message = "The loan value must be greater than 0")
    private Long loanValue;

    @PositiveOrZero(message = "The yearly interest rate must be greater than 0")
    @Min(value = 0, message = "The yearly interest rate should be greater than 0")
    @Max(value = 100, message = "The yearly interest rate should be lesser than 100")
    private Double yearlyInterestRate;

    @PositiveOrZero(message = "The loan term must be greater than 0")
    @Min(value = 0, message = "The loan term should be greater than 0")
    @Max(value = 30, message = "The loan term should be lesser than 30")
    private Integer loanTerm;
}

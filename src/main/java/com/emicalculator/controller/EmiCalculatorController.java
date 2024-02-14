package com.emicalculator.controller;

import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.emicalculator.controller.model.EmiDTO;
import com.emicalculator.domain.Emi;
import com.emicalculator.domain.EmiCalculator;
import com.emicalculator.domain.exception.InvalidInputForEmiCalculationException;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/v1")
public class EmiCalculatorController {

    private final EmiCalculator emiCalculator;

    public EmiCalculatorController(EmiCalculator emiCalculator) {
        this.emiCalculator = emiCalculator;
    }

    @PostMapping(value = "/calculate-emi", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Emi> calculate(@RequestBody @Valid EmiDTO emiDTO) throws InvalidInputForEmiCalculationException {

        return ResponseEntity.ok(
            new Emi(emiCalculator.calculate(emiDTO.getLoanValue(), emiDTO.getYearlyInterestRate(), emiDTO.getLoanTerm())));
    }


}

package com.emicalculator.controller;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import com.emicalculator.controller.model.EmiDTO;
import com.emicalculator.domain.EmiCalculator;
import com.fasterxml.jackson.databind.ObjectMapper;

@WebMvcTest(EmiCalculatorController.class)
class EmiCalculatorControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private EmiCalculator emiCalculator;

    @Test
    void whenValidInput_thenReturnEmiCalculated() throws Exception {

        ObjectMapper objectMapper = new ObjectMapper();
        String content = objectMapper.writeValueAsString(new EmiDTO(100000L, 7.0, 12));

        mockMvc.perform(
            MockMvcRequestBuilders.post("/api/v1/calculate-emi")
                .content(content)
                .accept(MediaType.APPLICATION_JSON)
                .contentType(MediaType.APPLICATION_JSON))
            .andExpect(status().isOk());
    }

    @Test
    void whenInvalidLoanTerm_thenReturnBadRequest() throws Exception {

        ObjectMapper objectMapper = new ObjectMapper();
        String content = objectMapper.writeValueAsString(new EmiDTO(100000L, 7.0, 40));

        mockMvc.perform(
                MockMvcRequestBuilders.post("/api/v1/calculate-emi")
                    .content(content)
                    .accept(MediaType.APPLICATION_JSON)
                    .contentType(MediaType.APPLICATION_JSON))
            .andExpect(status().isBadRequest())
            .andExpect(MockMvcResultMatchers.jsonPath("$.errors").value("The loan term should be lesser than 30"));
    }

    @Test
    void whenInputIsInvalid_thenReturnBadRequest() throws Exception {

        mockMvc.perform(
                MockMvcRequestBuilders.post("/api/v1/calculate-emi")
                    .content("")
                    .accept(MediaType.APPLICATION_JSON)
                    .contentType(MediaType.APPLICATION_JSON))
            .andExpect(status().isBadRequest());
    }

}
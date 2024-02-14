package com.emicalculator.config;

import org.springframework.context.annotation.Bean;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;

public class OpenApiConfig {

    @Bean
    public OpenAPI openAPI() {
        return new OpenAPI().info(new Info().title("EMI Calculator")
            .description("An application to calculate the EMI for different types of loans.")
            .version("v1.0"));
    }
}

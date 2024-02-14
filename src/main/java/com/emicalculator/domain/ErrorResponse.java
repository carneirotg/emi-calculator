package com.emicalculator.domain;

import java.util.List;

public record ErrorResponse(int statusCode, List<String> errors) {}

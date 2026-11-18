package com.ridesure.policy_service;

import java.math.BigDecimal;
import java.time.LocalDate;

import jakarta.validation.constraints.FutureOrPresent;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

public record CreateQuoteRequest(
        @NotBlank String customerName,
        @NotBlank String vehicleRegistration,
        @NotNull @Positive BigDecimal vehicleValue,
        @NotNull @FutureOrPresent LocalDate startDate) {
}
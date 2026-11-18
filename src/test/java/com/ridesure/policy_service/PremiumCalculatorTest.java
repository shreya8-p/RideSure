package com.ridesure.policy_service;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.math.BigDecimal;

import org.junit.jupiter.api.Test;

class PremiumCalculatorTest {

    private final PremiumCalculator calculator = new PremiumCalculator();

    @Test
    void premiumIsThreePercentOfVehicleValue() {
        BigDecimal premium = calculator.calculate(new BigDecimal("80000"));

        assertEquals(new BigDecimal("2400.00"), premium);
    }

    @Test
    void premiumIsRoundedToTwoDecimals() {
        BigDecimal premium = calculator.calculate(new BigDecimal("12345.67"));

        assertEquals(new BigDecimal("370.37"), premium);
    }
}
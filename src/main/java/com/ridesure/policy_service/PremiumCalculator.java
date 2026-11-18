package com.ridesure.policy_service;

import java.math.BigDecimal;
import java.math.RoundingMode;

import org.springframework.stereotype.Component;

@Component
public class PremiumCalculator {

    private static final BigDecimal RATE = new BigDecimal("0.03"); // 3% of vehicle value

    public BigDecimal calculate(BigDecimal vehicleValue) {
        return vehicleValue.multiply(RATE).setScale(2, RoundingMode.HALF_UP);
    }
}
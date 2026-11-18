package com.ridesure.policy_service;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.UUID;

public record PolicyResponse(
        UUID id,
        String policyNumber,
        String customerName,
        String vehicleRegistration,
        BigDecimal premium,
        PolicyStatus status,
        LocalDate startDate,
        LocalDate endDate) {

    public static PolicyResponse from(Policy p) {
        return new PolicyResponse(p.getId(), p.getPolicyNumber(), p.getCustomerName(),
                p.getVehicleRegistration(), p.getPremium(), p.getStatus(),
                p.getStartDate(), p.getEndDate());
    }
}
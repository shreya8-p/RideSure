package com.ridesure.policy_service;

import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.UUID;

@Entity
@Table(name = "policy")
public class Policy {

    @Id
    private UUID id;

    private String policyNumber;
    private String customerName;
    private String vehicleRegistration;
    private BigDecimal vehicleValue;
    private BigDecimal premium;

    @Enumerated(EnumType.STRING)
    private PolicyStatus status;

    private LocalDate startDate;
    private LocalDate endDate;

    protected Policy() {
        // JPA needs an empty constructor
    }

    public Policy(String customerName, String vehicleRegistration,
                  BigDecimal vehicleValue, BigDecimal premium, LocalDate startDate) {
        this.id = UUID.randomUUID();
        this.policyNumber = "RS-" + id.toString().substring(0, 8).toUpperCase();
        this.customerName = customerName;
        this.vehicleRegistration = vehicleRegistration;
        this.vehicleValue = vehicleValue;
        this.premium = premium;
        this.startDate = startDate;
        this.endDate = startDate.plusYears(1);
        this.status = PolicyStatus.QUOTED;
    }

    public void activate() {
        if (status != PolicyStatus.QUOTED) {
            throw new IllegalStateException("Only a quoted policy can be activated, but this one is " + status);
        }
        status = PolicyStatus.ACTIVE;
    }

    public void cancel() {
        if (status == PolicyStatus.CANCELLED) {
            throw new IllegalStateException("Policy is already cancelled");
        }
        status = PolicyStatus.CANCELLED;
    }

    public UUID getId() { return id; }
    public String getPolicyNumber() { return policyNumber; }
    public String getCustomerName() { return customerName; }
    public String getVehicleRegistration() { return vehicleRegistration; }
    public BigDecimal getVehicleValue() { return vehicleValue; }
    public BigDecimal getPremium() { return premium; }
    public PolicyStatus getStatus() { return status; }
    public LocalDate getStartDate() { return startDate; }
    public LocalDate getEndDate() { return endDate; }
}
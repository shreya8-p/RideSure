package com.ridesure.policy_service;

import java.math.BigDecimal;
import java.util.UUID;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class PolicyService {

    private final PolicyRepository policyRepository;
    private final PremiumCalculator premiumCalculator;

    public PolicyService(PolicyRepository policyRepository, PremiumCalculator premiumCalculator) {
        this.policyRepository = policyRepository;
        this.premiumCalculator = premiumCalculator;
    }

    @Transactional
    public Policy createQuote(CreateQuoteRequest request) {
        BigDecimal premium = premiumCalculator.calculate(request.vehicleValue());
        Policy policy = new Policy(request.customerName(), request.vehicleRegistration(),
                request.vehicleValue(), premium, request.startDate());
        return policyRepository.save(policy);
    }

    @Transactional(readOnly = true)
    public Policy getPolicy(UUID id) {
        return policyRepository.findById(id)
                .orElseThrow(() -> new PolicyNotFoundException(id));
    }

    @Transactional
    public Policy activate(UUID id) {
        Policy policy = getPolicy(id);
        policy.activate(); // saved automatically when the transaction ends
        return policy;
    }

    @Transactional
    public Policy cancel(UUID id) {
        Policy policy = getPolicy(id);
        policy.cancel();
        return policy;
    }
}
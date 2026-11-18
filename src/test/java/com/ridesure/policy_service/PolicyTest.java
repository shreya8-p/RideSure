package com.ridesure.policy_service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.math.BigDecimal;
import java.time.LocalDate;

import org.junit.jupiter.api.Test;

class PolicyTest {

    private Policy newPolicy() {
        return new Policy("Ravi Patil", "KA22AB1234",
                new BigDecimal("80000"), new BigDecimal("2400.00"),
                LocalDate.of(2026, 12, 1));
    }

    @Test
    void newPolicyStartsAsQuoted() {
        assertEquals(PolicyStatus.QUOTED, newPolicy().getStatus());
    }

    @Test
    void policyEndsOneYearAfterItStarts() {
        assertEquals(LocalDate.of(2027, 12, 1), newPolicy().getEndDate());
    }

    @Test
    void quotedPolicyCanBeActivated() {
        Policy policy = newPolicy();

        policy.activate();

        assertEquals(PolicyStatus.ACTIVE, policy.getStatus());
    }

    @Test
    void activePolicyCannotBeActivatedAgain() {
        Policy policy = newPolicy();
        policy.activate();

        assertThrows(IllegalStateException.class, policy::activate);
    }

    @Test
    void cancelledPolicyCannotBeCancelledAgain() {
        Policy policy = newPolicy();
        policy.cancel();

        assertThrows(IllegalStateException.class, policy::cancel);
    }
}
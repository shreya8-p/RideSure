package com.ridesure.policy_service;

import java.util.UUID;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/policies")
public class PolicyController {

    private final PolicyService policyService;

    public PolicyController(PolicyService policyService) {
        this.policyService = policyService;
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public PolicyResponse createQuote(@Valid @RequestBody CreateQuoteRequest request) {
        return PolicyResponse.from(policyService.createQuote(request));
    }

    @GetMapping("/{id}")
    public PolicyResponse get(@PathVariable UUID id) {
        return PolicyResponse.from(policyService.getPolicy(id));
    }

    @PostMapping("/{id}/activate")
    public PolicyResponse activate(@PathVariable UUID id) {
        return PolicyResponse.from(policyService.activate(id));
    }

    @PostMapping("/{id}/cancel")
    public PolicyResponse cancel(@PathVariable UUID id) {
        return PolicyResponse.from(policyService.cancel(id));
    }
}
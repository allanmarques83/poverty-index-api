package com.bb.domain.service;

import java.util.List;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.management.RuntimeErrorException;

import org.eclipse.microprofile.rest.client.inject.RestClient;

import com.bb.domain.integration.worldbank.WorldBankClient;
import com.bb.domain.service.dto.PovertyIndicatorResponseDto;

@ApplicationScoped
public class PovertyIndicatorService {
    @Inject @RestClient WorldBankClient client;

    public List<PovertyIndicatorResponseDto> getPovertyIndicator(String country) {
        try {
            return client.getPovertyIndicator("json", country).toPovertyIndicator();
        } catch(Exception e) {
            throw new RuntimeErrorException(null, e.getMessage());
        }
    }
}

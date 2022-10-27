package com.bb.domain.service;

import java.util.List;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;

import org.eclipse.microprofile.rest.client.inject.RestClient;

import com.bb.domain.integration.worldbank.WorldBankClient;
import com.bb.domain.integration.worldbank.dto.WorldBankCountriesDto;
import com.bb.domain.service.dto.CountriesResponseDto;

@ApplicationScoped
public class CountriesService {
    @Inject @RestClient WorldBankClient client;

    public List<CountriesResponseDto> getCountries() {
        WorldBankCountriesDto dto = client.getCountry();
        return dto.parseToCountriesResponseDto();
    }
}

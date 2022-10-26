package com.bb.domain.service;

import java.util.List;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;

import org.eclipse.microprofile.rest.client.inject.RestClient;

import com.bb.domain.integration.worldbank.WorldBankClient;
import com.bb.domain.integration.worldbank.dto.Countries;
import com.bb.domain.service.dto.CountriesResponseDto;

@ApplicationScoped
public class CountryService {
    @Inject @RestClient WorldBankClient client;

    public List<CountriesResponseDto> getCountries() {
        Countries dto = client.getCountry();
        System.out.println(dto);
        return dto.parseToCountriesResponseDto();
    }
}

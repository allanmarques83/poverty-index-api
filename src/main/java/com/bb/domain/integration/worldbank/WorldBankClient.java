package com.bb.domain.integration.worldbank;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Singleton;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;

import org.eclipse.microprofile.rest.client.inject.RegisterRestClient;

import com.bb.domain.integration.worldbank.dto.WorldBankCountriesDto;
import com.bb.domain.integration.worldbank.dto.WorldBankPovertyDto;

@Singleton
@ApplicationScoped
@RegisterRestClient(configKey="worldbank")
public interface WorldBankClient {
    @GET
    @Path("v2/country/{country}/indicator/SI.POV.DDAY")
    @Produces(MediaType.APPLICATION_JSON)
    WorldBankPovertyDto getPovertyIndicator(
        @QueryParam("format") String format,
        @PathParam("country") String country
    );

    @GET
    @Path("v2/country")
    @Consumes(MediaType.APPLICATION_XML)
    @Produces(MediaType.APPLICATION_JSON)
    WorldBankCountriesDto getCountry();
}

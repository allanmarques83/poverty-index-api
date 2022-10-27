package com.bb.domain.web;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.eclipse.microprofile.openapi.annotations.Operation;
import org.eclipse.microprofile.openapi.annotations.responses.APIResponses;
import org.eclipse.microprofile.openapi.annotations.responses.APIResponse;
import org.eclipse.microprofile.openapi.annotations.media.Content;
import org.eclipse.microprofile.openapi.annotations.media.Schema;

import com.bb.domain.exception.dto.ApiExceptionDto;
import com.bb.domain.service.CountriesService;
import com.bb.domain.service.dto.CountriesResponseDto;

@Path("/countries")
@ApplicationScoped
public class CountryController {
    @Inject CountriesService service;

    @GET
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    @Operation(summary = "Get a list of available countries for consult poverty indicator")
    @APIResponses(value = {
        @APIResponse(responseCode = "200", description = "OK", content = @Content(
            mediaType = "application/json", schema = @Schema(implementation = CountriesResponseDto[].class)
        )),
        @APIResponse(responseCode = "400", description = "Bad Request", content = @Content(
            mediaType = "application/json", schema = @Schema(implementation = ApiExceptionDto.class)
        ))
    })
    public Response getCountries() {
        return Response.ok(service.getCountries()).build();
    }
}

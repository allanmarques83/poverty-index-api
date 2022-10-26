package com.bb.domain.web;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import com.bb.domain.service.CountryService;

@Path("/countries")
@ApplicationScoped
public class CountryController {
    @Inject CountryService service;

    @GET
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response getCountries() {
        return Response.ok(service.getCountries()).build();
    }
}

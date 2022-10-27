package com.bb.config;

import javax.ws.rs.core.Application;

import org.eclipse.microprofile.openapi.annotations.OpenAPIDefinition;
import org.eclipse.microprofile.openapi.annotations.info.Info;
import org.eclipse.microprofile.openapi.annotations.tags.Tag;

@OpenAPIDefinition(
    tags = {@Tag(name = "Poverty indicator", description = "REST service")},
    info = @Info(title = "World Development Indicators",version = "0.0.1")
)
public class Swagger extends Application {}

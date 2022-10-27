package com.bb.config;

import java.util.HashMap;
import java.util.Map;

import com.github.tomakehurst.wiremock.WireMockServer;

import io.quarkus.test.common.QuarkusTestResourceLifecycleManager;

import static com.github.tomakehurst.wiremock.client.WireMock.urlEqualTo;
import static com.github.tomakehurst.wiremock.client.WireMock.get;
import static com.github.tomakehurst.wiremock.client.WireMock.aResponse;

public class TestResource implements QuarkusTestResourceLifecycleManager {
    private WireMockServer wireMockServer;

    @Override
    public Map<String, String> start() {
        wireMockServer = new WireMockServer(9569);
        wireMockServer.start();

        createSuccessfulRestResponse();
        createBadRequestRestResponse();

        final Map<String, String> configuration = new HashMap<>();
        configuration.put("quarkus.rest-client.worldbank.url", wireMockServer.baseUrl());

        return configuration;
    }

    @Override
    public void stop() {
        wireMockServer.shutdown();
    }

    private void createSuccessfulRestResponse() {
        wireMockServer.stubFor(
            get(
                urlEqualTo("/v2/country/BRA/indicator/SI.POV.DDAY?format=json")
            ).willReturn(
                aResponse()
                    .withStatus(200)
                    .withHeader("Content-Type", "application/json")
                    .withBodyFile("poverty.json")
            )
        );

        wireMockServer.stubFor(
            get(
                urlEqualTo("/v2/country")
            ).willReturn(
                aResponse()
                    .withStatus(200)
                    .withHeader("Content-Type", "text/xml")
                    .withBodyFile("countries.xml")
            )
        );
    }
    
    private void createBadRequestRestResponse() {
        wireMockServer.stubFor(
            get(
                urlEqualTo("/v2/country/USA/indicator/SI.POV.DDAY?format=json")
            ).willReturn(
                aResponse()
                    .withStatus(400)
                    .withHeader("Content-Type", "application/json")
                    .withBody("{\"error\": \"error message\"}")
            )
        );
    }

    @Override
    public void inject(TestInjector testInjector) {
        testInjector.injectIntoFields(
            wireMockServer, 
            new TestInjector.AnnotatedAndMatchesType(InjectMockServer.class, WireMockServer.class)
        );
    }
}

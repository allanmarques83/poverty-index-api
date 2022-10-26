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
        // configuration.put("quarkus.rest-client.worldbank.url", wireMockServer.baseUrl());
        configuration.put("quarkus.rest-client.worldbank.url", "http://api.worldbank.org");

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
                    .withBody(
                        """
                        [ {
                            \"page\": 1, 
                            \"pages\": 2, 
                            \"per_page\": 50,
                            \"total\": 62,
                            \"sourceid\": \"2\",
                            \"sourcename\": \"World Development Indicators\",
                            \"lastupdated\": \"2022-09-16\"
                        }, [ {
                            \"indicator\": {
                              \"id\": \"SI.POV.DDAY\",
                              \"value\": \"Poverty headcount ratio at $2.15 a day (2017 PPP) (% of population)\"
                            },
                            \"country\": {
                             \"id\": \"BR\",
                              \"value\": \"Brazil\"
                            },
                            \"countryiso3code\": \"BRA\",
                            \"date\": \"2021\",
                            \"value\": 1.69,
                            \"unit\": \"\",
                            \"obs_status\": \"\",
                            \"decimal\": 1
                          } ] ]
                    """
                    )
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

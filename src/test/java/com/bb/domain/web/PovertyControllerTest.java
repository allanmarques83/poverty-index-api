package com.bb.domain.web;

import org.hamcrest.Matchers;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import com.bb.config.InjectMockServer;
import com.bb.config.TestResource;
import com.github.tomakehurst.wiremock.WireMockServer;

import io.quarkus.test.common.QuarkusTestResource;
import io.quarkus.test.junit.QuarkusTest;
import io.restassured.RestAssured;

@QuarkusTest
@QuarkusTestResource(TestResource.class)
class PovertyControllerTest {
    @InjectMockServer WireMockServer mockServer;

    @Test
    @DisplayName("must return poverty indicator by country")
    void shouldReturnPovertyIndicator() {
        RestAssured.given()
            .header("Content-type", "application/json")
            .when()
            .get("/poverty/BRA")
            .then()
            .statusCode(200)
            .body("[0].id", Matchers.is(1))
            .body("[0].year", Matchers.is("2021"))
            .body("[0].index", Matchers.is(1.69F))
            .body("[0].country", Matchers.is("Brazil"))
            .extract()
            .response()
            .prettyPrint();
    }

    @Test
    @DisplayName("must return a custom error body")
    void shouldReturnBadRequest() {
        RestAssured.given()
            .header("Content-type", "application/json")
            .when()
            .get("/poverty/USA")
            .then()
            .statusCode(400)
            .body("status", Matchers.is(400))
            .body("message", Matchers.is("Unknown error, status code 400"))
            .extract()
            .response()
            .prettyPrint();
    }
}

package com.bb.domain.web;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import com.bb.config.TestResource;

import io.quarkus.test.common.QuarkusTestResource;
import io.quarkus.test.junit.QuarkusTest;
import io.restassured.RestAssured;

@QuarkusTest
@QuarkusTestResource(TestResource.class)
class CountryControllerTest {
    @Test
    @DisplayName("must return poverty indicator by country")
    void shouldReturnPovertyIndicator() {
        RestAssured.given()
            .header("Content-type", "application/json")
            .when()
            .get("/countries")
            .then()
            .statusCode(400)
            .extract()
            .response()
            .prettyPrint();
    }
}

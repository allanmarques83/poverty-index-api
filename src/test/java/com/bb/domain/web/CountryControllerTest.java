package com.bb.domain.web;

import org.hamcrest.Matchers;
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
    @DisplayName("must return countries")
    void shouldReturnPovertyIndicator() {
        RestAssured.given()
            .header("Content-type", "application/json")
            .when()
            .get("/countries")
            .then()
            .statusCode(200)
            .body("[0].country", Matchers.is("Aruba"))
            .body("[0].code", Matchers.is("ABW"))
            .extract()
            .response()
            .prettyPrint();
    }
}

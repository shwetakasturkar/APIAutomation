package userManagement;

import io.restassured.RestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.specification.*;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.hasItems;

public class BuilderPatternImplementation {
    private RequestSpecification requestSpec;
    private ResponseSpecification responseSpec;

    @Test
    public void testRestAssuredNormalApproach() {
        RestAssured.baseURI = "https://jsonplaceholder.typicode.com";

        Response response =given()
                .contentType(ContentType.JSON)
                .queryParam("userId", "1")
                .header("Authorization", "Bearer my-token")
                .when()
                .get("/posts")
                .then()
                .assertThat()
                .statusCode(200).extract().response();

        assertThat(response.jsonPath().getList("email"),hasItems("abc","def"));
    }


    @Test
    private void testRestAssuredBuilderPattern() {
        requestSpec = new RequestSpecBuilder()
                .setBaseUri("https://jsonplaceholder.typicode.com")
                .setContentType("application/json")
                .addQueryParam("userId", "1")
                .addHeader("Authorization", "Bearer my-token").build();

        given()
                .spec(requestSpec)
                .when()
                .get("/posts")
                .then()
                .spec(getResponseSpec());
    }

    private ResponseSpecification getResponseSpec() {
        responseSpec = new ResponseSpecBuilder().
                expectStatusCode(200)
                .expectContentType("application/json")
                .build();


        return responseSpec;
    }
}




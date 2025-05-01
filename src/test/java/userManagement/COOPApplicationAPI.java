package userManagement;

import core.StatusCode;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;
import static junit.framework.Assert.assertEquals;
import static org.testng.Assert.assertTrue;

public class COOPApplicationAPI {



    @Test
    public void addNameandJobChicken() {

        Response response = given()
                .header("Content-Type", "application/json")
                .header("Authorization","Bearer a26ede1c63ec581a997b4df97428b08476f09317")
                .when().post("http://coop.apps.symfonycasts.com/api/4491/eggs-collect");
        assertEquals(response.statusCode(), StatusCode.UNAUTHORIZED.code);
        System.out.println(response.body().asString());
//        assertTrue(response.getBody().path("error_description"));
        assertEquals(response.getBody().path("error_description"),"The access token provided has expired");

    }

    @Test
    public void addNameandJobChickenform() {

        Response response = given()
                .header("Content-Type", "application/x-www-form-urlencoded")
                .formParam("client-id","TestWithSidharth")
                .formParam("client-secret","c506f6a65f3566468cac23adfd3da1a6")
                .formParam("grant-type","client-credentials")
                .when().post("client-credentials");
        assertEquals(response.statusCode(), StatusCode.IN_VALID.code);
        System.out.println(response.body().asString());
//        assertTrue(response.getBody().path("error_description"));
//        assertEquals(response.getBody().path("error_description"),"The access token provided has expired");

    }
}

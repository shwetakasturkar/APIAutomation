package userManagement;

import io.restassured.response.Response;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;

public class getErgast {

    @Test
    public void validateGetResponseBodyPathParams() {

        String raceSeasonValue = "2024";

        Response response = given().
                queryParam("page", 2).
                queryParam("per_page", "6")
                .pathParams("raceSeason", raceSeasonValue)
                .when().
                get("http://ergast.com/api/f1/{raceSeason}/circuits.json")
                .then().statusCode(200).extract().response();


    }
}

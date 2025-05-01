package userManagement;

import io.restassured.response.Response;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;
import static org.assertj.core.api.Assertions.assertThat;
import static utils.SoftAssertionUtil.assertEquals;

public class APIChaining {

    public String ApiChaining()
    {
        Response response = given().
                header("Content-Type","application/json").body("{\"userName\":\"TOOLSQA-Test\",\"password\":\"Test@@123\"}").
                when().post("https://bookstore.toolsqa.com/Account/v1/GenerateToken");
        assertThat(response.then().statusCode(200));
        System.out.println(response.body().asString());


        String token = response.path("token");
        return token;

    }

    @Test
    public void takingAuthorization()
    {

        String toaken = ApiChaining();
        Response response = given().header("Authorization","Bearer"+toaken).
                header("Content-Type","application/json").body("\"{\\\"userId\\\":\\\"6fdcd89a-7efd-407e-b5ae-7d873cb9c16f\\\",\\\"collectionOfIsbns\\\":[{\\\"isbn\\\":\\\"9781449325862\\\"}]}\"").
                when().post("https://bookstore.toolsqa.com/BookStore/v1/Books");
        assertThat(response.then().statusCode(200));
        System.out.println(response.body().asString());



    }
}

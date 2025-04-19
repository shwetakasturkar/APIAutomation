package userManagement;

import io.restassured.response.Response;
import org.testng.annotations.Test;
import utils.JsonReader;
import utils.PropertyReader;

import java.io.IOException;
import java.text.ParseException;

import static io.restassured.RestAssured.given;
import static junit.framework.Assert.assertEquals;

public class getPostManEcho {
    @Test(groups = "SmokeSuite")
    public void usingBasicAuthorization() throws IOException, ParseException, org.json.simple.parser.ParseException {
        String UserName = JsonReader.getdatafromjson("UserName");
        String Password = JsonReader.getdatafromjson("Password");
        String URL = PropertyReader.PropertyReader("config.properties", "ServerAddress");
        String edpnt = JsonReader.getdatafromjson("ServerEndPoint");
        String url = URL+edpnt;


        Response response = given()
                .auth()
                .basic(UserName, Password)
                .when()
                .get(url);
        int StatusCode = response.statusCode();
        assertEquals(StatusCode, core.StatusCode.SUCCESS.code);
        System.out.println(response.body().asString());


    }

    public void usingDigestAuthorization() throws IOException, ParseException, org.json.simple.parser.ParseException {
        String UserName = JsonReader.getdatafromjson("UserName");
        String Password = JsonReader.getdatafromjson("Password");
        String URL = PropertyReader.PropertyReader("config.properties", "ServerAddress");
        String edpnt = JsonReader.getdatafromjson("ServerEndPo");
        String url = URL+edpnt;
        Response response = given()
                .auth()
                .digest("postman", "password")
                .when()
                .get(url);

    }

}

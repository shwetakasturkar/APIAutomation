package userManagement;

import core.BaseTest;
import io.restassured.RestAssured;
import io.restassured.module.jsv.JsonSchemaValidator;
import org.testng.annotations.Test;
import utlis.ExtentReport;
import utlis.PropertyReader;

import java.io.File;
import java.io.IOException;
import utlis.ExtentReport;
import static io.restassured.RestAssured.given;

public class JsonSchemaValidation extends BaseTest {

    @Test
    public void getUsersData() throws IOException {
        ExtentReport.extentlog = ExtentReport.extentreport.startTest("Validating Users", "Check Response COde");
        String serverAddress = PropertyReader.PropertyReader("config.properties", "server");
        System.out.println(serverAddress);
        given().when().get(serverAddress)
                .then().assertThat().
                statusCode(200);
        File file = new File("resources//ExpectedJsonSchema.json");
        RestAssured.given().get(serverAddress)
                .then().body(JsonSchemaValidator.matchesJsonSchema(file));
    }

}

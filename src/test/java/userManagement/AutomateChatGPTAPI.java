package userManagement;

import core.StatusCode;
import junit.framework.Assert;
import org.testng.annotations.Test;
import io.restassured.response.Response;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.isEmptyString;
import static org.hamcrest.Matchers.not;
import static utils.SoftAssertionUtil.assertEquals;

public class AutomateChatGPTAPI {

    @Test(description="Validate Status for the List Models")
    public void validateGetResponseinChatGPT() {
       Response resp= given().header("Authorization","Bearer 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")
                .when()
                .get("https://api.openai.com/v1/models")
                .then()
                .assertThat()
                .statusCode(200)
                .body(not(isEmptyString())).extract().response();
      Assert.assertEquals(resp.statusCode(), StatusCode.SUCCESS.code);
        System.out.println(resp.body().asString());

    }
    @Test(description="Validate Status for the List Models")
    public void validateGetinChatGPTRetrieveList() {
        Response resp= given().header("Authorization","Bearer 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")
//                .pathParams("model_id","gpt-4o")
                .when()
                .get("https://api.openai.com/v1/models/gpt-4o")
                .then()
                .assertThat()
                .statusCode(200)
                .body(not(isEmptyString())).extract().response();
        Assert.assertEquals(resp.statusCode(), StatusCode.SUCCESS.code);
        System.out.println(resp.body().asString());

    }
}

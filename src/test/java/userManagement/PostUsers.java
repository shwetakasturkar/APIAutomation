package userManagement;


import core.StatusCode;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.apache.commons.io.IOUtils;
import org.testng.annotations.Test;
import pojo.CityRequest;
import pojo.PostRequestBody;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;


import static io.restassured.RestAssured.given;
import static junit.framework.Assert.assertEquals;

public class PostUsers {

    private  static FileInputStream UsingFileInputStream(String RequestBodyFileName){
        FileInputStream fis = null;
        try {
            fis = new FileInputStream
                    (new File
                            (System.getProperty("user.dir")+"/resources/TestData/"+RequestBodyFileName));
        }
        catch (FileNotFoundException e)
        {
            e.printStackTrace();
        }
        return fis;
    }

    @Test
    public void addNameandJob() {

        RestAssured.baseURI = "https://reqres.in/api/users";

        Response response = given()
                .header("Content-Type", "application/json")
                .body("{\"name\":\"Shweta\",\"job\":\"SDET\"}")
                .when().post();
        assertEquals(response.statusCode(), StatusCode.CREATED.code);
        System.out.println(response.body().asString());

    }
    @Test
    public void addNameandJobPatch() {

        RestAssured.baseURI = "https://reqres.in/api/users/2";

        Response response = given()
                .header("Content-Type", "application/json")
                .body("{\"name\":\"ABCD\"}")
                .when().patch();
        assertEquals(response.statusCode(), StatusCode.SUCCESS.code);
        System.out.println(response.body().asString());

    }

    @Test
    public void addNameandJobPut() {

        RestAssured.baseURI = "https://reqres.in/api/users/2";

        Response response = given()
                .header("Content-Type", "application/json")
                .body("{\"name\":\"Shweta\",\"job\":\"SDET\"}")
                .when().put();
        assertEquals(response.statusCode(), StatusCode.SUCCESS.code);
        System.out.println(response.body().asString());

    }
    @Test
    public void usingFile() throws IOException {

        RestAssured.baseURI = "https://reqres.in/api/users";

        Response response = given()
                .header("Content-Type", "application/json")
                .body(IOUtils.toString
                        (UsingFileInputStream("POstRequestBody.json")))
                .when().post();
        assertEquals(response.statusCode(), StatusCode.CREATED.code);
        System.out.println(response.body().asString());

    }

    @Test
    public void usingFilePut() throws IOException {



        Response response = given()
                .header("Content-Type", "application/json")
                .body(IOUtils.toString
                        (UsingFileInputStream("POstRequestBody.json")))
                .when().put("https://reqres.in/api/users/2");
        assertEquals(response.statusCode(), StatusCode.SUCCESS.code);
        System.out.println(response.body().asString());

    }

    @Test
    public void usingFilePatch() throws IOException {
        Response response = given()
                .header("Content-Type", "application/json")
                .body(IOUtils.toString
                        (UsingFileInputStream("PatchRequestBosy.json")))
                .when().patch("https://reqres.in/api/users/2");
        assertEquals(response.statusCode(), StatusCode.SUCCESS.code);
        System.out.println(response.body().asString());

    }

    @Test
    public void usingPOJOPost() {
        PostRequestBody postReqBody = new PostRequestBody();
        postReqBody.setJob("leader");
        postReqBody.setName("morpheus");

        RestAssured.baseURI = "https://reqres.in/api/users";

        Response response = given()
                .header("Content-Type", "application/json")
                .body(postReqBody)
                .when().post();
        assertEquals(response.statusCode(), StatusCode.CREATED.code);
        System.out.println(response.body().asString());

    }

    @Test
    public void usingPOJOPostUsingList() {
        List<String> listOfLanguage = new ArrayList<>();
        listOfLanguage.add("Java");
        listOfLanguage.add("Python");
        PostRequestBody postReqBody = new PostRequestBody();
        postReqBody.setJob("leader");
        postReqBody.setName("morpheus");
        postReqBody.setLanguages(listOfLanguage);

        RestAssured.baseURI = "https://reqres.in/api/users";

        Response response = given()
                .header("Content-Type", "application/json")
                .body(postReqBody)
                .when().post();
        assertEquals(response.statusCode(), StatusCode.CREATED.code);
        System.out.println(response.body().asString());

    }
    @Test
    public void usingPutPojo() throws IOException {
        PostRequestBody postReqBody = new PostRequestBody();
        postReqBody.setJob("leader");
        postReqBody.setName("morpheus");


        Response response = given()
                .header("Content-Type", "application/json")
                .body(postReqBody)
                .when().put("https://reqres.in/api/users/2");
        assertEquals(response.statusCode(), StatusCode.SUCCESS.code);
        System.out.println(response.body().asString());

    }

    public void usingPatchPojo() throws IOException {
        PostRequestBody postReqBody = new PostRequestBody();
        postReqBody.setName("morpheus");


        Response response = given()
                .header("Content-Type", "application/json")
                .body(postReqBody)
                .when().put("https://reqres.in/api/users/2");
        assertEquals(response.statusCode(), StatusCode.SUCCESS.code);
        System.out.println(response.body().asString());

    }
    @Test
    public void usingPOJOPostUsingListComplex() {
        List<String> listOfLanguage = new ArrayList<>();
        listOfLanguage.add("Java");
        listOfLanguage.add("Python");
        PostRequestBody postReqBody = new PostRequestBody();
        postReqBody.setJob("leader");
        postReqBody.setName("morpheus");
        postReqBody.setLanguages(listOfLanguage);
        CityRequest DelhiCR = new CityRequest();
        DelhiCR.setName("Delhi");
        DelhiCR.setTemp("30.8");
        CityRequest BangloreCR = new CityRequest();
        BangloreCR.setName("Banglore");
        BangloreCR.setTemp("40");
        List<CityRequest> crst = new ArrayList<>();
        crst.add(DelhiCR);
        crst.add(BangloreCR);
        postReqBody.setCityRequestBody(crst);

        RestAssured.baseURI = "https://reqres.in/api/users";

        Response response = given()
                .header("Content-Type", "application/json")
                .body(postReqBody)
                .when().post();
        PostRequestBody prb = response.as(PostRequestBody.class);
        prb.getName();
        prb.getJob();
        prb.getLanguages();
        prb.getCityRequestBody();
        System.out.println(prb.getCityRequestBody().get(0).getName());
        System.out.println(prb.getCityRequestBody().get(0).getTemp());
        System.out.println(prb.getCityRequestBody().get(1).getName());
        System.out.println(prb.getCityRequestBody().get(1).getTemp());
        assertEquals(prb.getJob(),"leader");
        assertEquals(prb.getLanguages().get(0),"Java");
        assertEquals(response.statusCode(), StatusCode.CREATED.code);
        System.out.println(response.body().asString());

    }
}

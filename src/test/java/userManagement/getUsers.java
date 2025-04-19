package userManagement;


import core.BaseTest;
import io.restassured.RestAssured;
import io.restassured.http.Cookie;
import io.restassured.http.Cookies;
import io.restassured.http.Header;
import io.restassured.http.Headers;
import io.restassured.response.Response;
import org.json.simple.JSONArray;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;
import utils.ExtentReport;
import utils.JsonReader;
import utils.PropertyReader;
import utils.SoftAssertionUtil;

import java.io.IOException;
import java.text.ParseException;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import static io.restassured.RestAssured.given;
import static junit.framework.Assert.assertEquals;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;
import static utils.JsonReader.getJsonArray;
import static utils.JsonReader.getjsondataArray;

public class getUsers extends BaseTest {

    @BeforeClass
    public void setup() {
        RestAssured.baseURI = "https://reqres.in/api";
    }

    @Test
    public void getUsersData() throws IOException {
        ExtentReport.logInfo("validateResponseBodyGetPathParam");
        String serverAddress = PropertyReader.PropertyReader("config.properties", "server");
        System.out.println(serverAddress);
        given().when().get(serverAddress)
                .then().assertThat().
                statusCode(200);

    }

    @Test
    public void validateGetResponseBody() {
        RestAssured.baseURI = "https://jsonplaceholder.typicode.com/";

        given().when().get("/todos/1").then().assertThat().statusCode(200).body(not(isEmptyString())).body("title", equalTo("delectus aut autem")).body("id", equalTo(1));
    }

    @Test
    public void validateGetResponseBodyContains() {

        ExtentReport.logInfo("validateResponseBodyGetPathParam");
;        RestAssured.baseURI = "https://jsonplaceholder.typicode.com/";

        Response response = given().when().get("/todos/");
//                .then().extract().response();

        assertThat(response.jsonPath().getList(""), hasSize(200));
        assertThat(response.jsonPath().getList("title"), hasItems("delectus aut autem", "quis ut nam facilis et officia qui"));
        response.then().body("data[0].emailid",equalTo(""));
    }

    @Test
    public void validateGetResponseBodyHasItems() {
        RestAssured.baseURI = "https://jsonplaceholder.typicode.com/comments";

        Response response = given().when().get("/posts");
//                .then().extract().response();
//        List<String> list = Arrays.asList("Eliseo@gardner.biz", "Jayne_Kuhic@sydney.com", "Nikita@garfield.biz");
//        assertThat(response.jsonPath().getList("email"), contains(list.toArray(new String[0])));


    }

    @Test
    public void validateGetResponseBodyisMatchers() throws IOException, ParseException, org.json.simple.parser.ParseException {
        String ServerAddress = PropertyReader.PropertyReader("config.properties", "Server");
        String endpoint = JsonReader.getdatafromjson("endpoint");
        String URL = ServerAddress + endpoint;
        System.out.println(URL);

        Response response = given().when().get(URL);
//                .then().extract().response();

//response.then().body("data",hasItems(1));
        response.then().body("data", hasSize(6));

        response.then().body("data[0].id", is(1));
        response.then().body("data[0].email", is("george.bluth@reqres.in"));
        response.then().body("data[0].first_name", is("George"));
        response.then().body("data[0].last_name", is("Bluth"));
        response.then().body("data[0].avatar", is("https://reqres.in/img/faces/1-image.jpg"));
    }

    @Test
    public void validateGetResponseBodyQueryParams()  {
        ExtentReport.logInfo("validateResponseBodyGetPathParam");

        RestAssured.baseURI = "https://reqres.in/api/";

        Response response = given().
                queryParam("page", 2).queryParam("per_page", "6")
                .when().get("users?page=2")
                .then().statusCode(200).extract().response();
        System.out.println(response.body().asString());
//

    }




    @Test
    public void formParam() {
        Response response = given().contentType("application/x-www-form-urlencoded").
                formParam("userName", "JohnDoe").
                formParam("job", "developer")
                .when().get("/users").then().statusCode(200).extract().response();
        System.out.println(response.body().asString());

//        response.then().body("userName",equalTo("JohnDoe"));
//        response.then().body("job",equalTo("developer"));
    }

    @Test

    public void getUserListWithHeaders() {

        given().header("Content-Type", "application/json")
                .header("Authroization", "bearer frhfQjkhjasdjsAszhdfjkhjxjkl")
                .when()
                .get("https://reqres.in/api/user?page=2")
                .then()
                .statusCode(200);

//        body("data[0].first_name",is("Michale"))
//                .body("data[0].last_name",is("Lawson"));

    }

    @Test
    public void testTwoHeadersWithMap() {
        Map<String, String> headers = new HashMap<>();
        headers.put("Content-Type", "application/json");
        headers.put("Authorization", "bearer <your token>");
        given()
                .headers(headers).
                when().get("https://reqres.in/api/user?page=2").then()
                .statusCode(200).body("page", equalTo(2));

    }

    @Test(groups = {"SmokeSuite","RegressionSuite"})
    public void usingCookfetchAllHeaders() {
        Response response = given()
                .when()
                .get("https://reqres.in/api/user?page=2")
                .then()
                .statusCode(200).extract().response();

        Headers getHeader = response.getHeaders();
        for (Header header : getHeader)
        {
            System.out.println("Header Name" + header.getName() + "Header Value" + header.getValue());
        }

    }

    @Test(groups = "RegressionSuite")
    public void usingCookies() {
        Cookie myCookie = new Cookie.Builder("Key1", "KesValue")
                .setComment("Setting cookie Details")
                .build();
        Response response = given()
                .cookie(myCookie)
                .when()
                .get("https://reqres.in/api/user?page=2")
                .then()
                .statusCode(200).extract().response();
        Cookies cookie = response.getDetailedCookies();
        for (Cookie coo : cookie)
        {
            coo.getName();
            coo.getValue();
        }
        Map<String, String> map = response.getCookies();
//        assertThat(map, hasKey("hgsdhcjs"));
//        assertThat(map, hasValue("jnscjnc"));

    }




@Test
    public void deleteUaer() {
        Response response = given()
                .when()
                .delete("https://reqres.in/api/user?page");
        assertEquals(response.statusCode(), 204);
    }

    @Test
    public void softAssertion() {

        System.out.println("softAssert");
        SoftAssertionUtil.assertTrue(false,"");
        SoftAssertionUtil.assertTrue(true,"");
        SoftAssertionUtil.assertTrue(false,"");
        SoftAssertionUtil.assertAll();


    }

    @DataProvider(name = "testdata")
    public Object[][] testdata() {
        return new Object[][]
                {
                        {1, "John"},
                        {2, "Ira"},
                        {3, "Dino"}
                };
    }
    @Test(dataProvider = "testdata")
    @Parameters({"id","name"})
    public void testendoint(int id, String name)
    {
given()
        .param("id",id)
        .param("name",name)
        .when().get("/endpoint").then()
        .statusCode(200);
    }

    @Test
    public void Test() throws IOException, ParseException, org.json.simple.parser.ParseException {
        getjsondataArray("languages", 0);


// Access the "books" array and print each item
        JSONArray jsonArray = getJsonArray("Contact details");
        Iterator<String> itr = jsonArray.iterator();
        while(itr.hasNext())
        {
            System.out.println(itr.next());
        }

    }
}







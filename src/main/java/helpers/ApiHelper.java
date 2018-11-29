package helpers;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.junit.Assert;

import static io.restassured.RestAssured.given;

public class ApiHelper {

    public static RequestSpecification prepareRequest() {
        RestAssured.baseURI = FileLoader.getBaseURL();
        return given().contentType(ContentType.JSON)
                .when().log().all();
    }

    public static Response get(String URL) {
        Response response = prepareRequest().get(URL);
        response.then().log().all();
        checkStatusCode(response, 200);
        return response;
    }

    public static Response delete(String URL) {
        Response response = prepareRequest().delete(URL);
        response.then().log().all();
        checkStatusCode(response, 200);
        return response;
    }

    public static void checkStatusCode(Response response, int code) {
        Assert.assertEquals("Received incorrect response code", response.getStatusCode(), code);
    }


}

package com.cydeo.Day2;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.*;
import static org.junit.jupiter.api.Assertions.*;

public class SpartanGetRequest {


    // given accept type application/json
    //when user send get request to api/spartans end point
    //then response content type must be application/json
//    and response content type must be application/json
    //and response body should include spartan result

    String baseUrl = "http://3.87.229.116:8000";

    @Test
    public void test1() {


        Response response = given().accept(ContentType.JSON).when().get(baseUrl + "/api/spartans");

        System.out.println("response.statusCode() = " + response.statusCode());
        System.out.println("response.contentType() = " + response.contentType());
        response.prettyPrint();
        assertEquals(response.statusCode(), 200);
        assertEquals(response.contentType(), "application/json");


    }

    /*
   Given accept header is application/json
   When users sends a get request to /api/spartans/3
   Then status code should be 200
   And content type should be application/json
   and json body should contain Fidole
*/
    @DisplayName("Get one spartan/api/spans/3 and verify")
    @Test
    public void test2() {

        Response response = given().accept(ContentType.JSON).when().get(baseUrl + "/api/spartans/3");

        assertEquals(response.statusCode(), 200);

        assertEquals(response.contentType(), "application/json");

        Assertions.assertTrue(response.body().asString().contains("Fidole"));
    }

        /*
        Given no headers provided
        When Users sends GET request to /api/hello
        Then response status code should be 200
        And Content type header should be “text/plain;charset=UTF-8”
        And header should contain date
        And Content-Length should be 17
        And body should be “Hello from Sparta"
        */

    @DisplayName("GET request to /api/hello")
    @Test
    public void test3() {

        Response response = RestAssured.when().get(baseUrl + "/api/hello");

        assertEquals(200, response.statusCode());

        assertEquals("text/plain;charset=UTF-8",response.contentType());

        Assertions.assertTrue(response.headers().hasHeaderWithName("Date"));

        System.out.println("response.header(\"Content-Length\") = " + response.header("Content-Length"));
        System.out.println(response.header("Date"));

        assertEquals("17",response.header("Content-Length"));

        assertEquals("Hello from Sparta",response.body().asString());




    }


}

package com.cydeo.Day5;

import com.cydeo.Day4.SpartanWithJsonPath;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;
import static io.restassured.RestAssured.*;


public class HamcrestMatchersApiTest extends SpartanWithJsonPath {

        /*
       given accept type is Json
       And path param id is 15
       When user sends a get request to spartans/{id}
       Then status code is 200
       And content type is Json
       And json data has following
           "id": 15,
           "name": "Meta",
           "gender": "Female",
           "phone": 1938695106
        */

    @DisplayName("OneSpartan with Hamcrest and chaining")
    @Test
    public void test1(){
        given().accept(ContentType.JSON).and()
                .pathParam("id", 15).when()
                .get("/api/spartans/{id}").then()
                .statusCode(200).and()
                .assertThat().contentType("application/json").and()
                .body("id",equalTo(15),"name",is("Meta"),"gender",is("Female"),"phone",is(1938695106)).log().all();



    }
// something wrong 404 not found
    @DisplayName("CBTraining Teacher request with chaining and matchers")
    @Test
    public void teacherData(){

        given().accept(ContentType.JSON).and()
                .pathParam("id",10423).when()
                .get("http://api.cybertektraining.com/teacher/{id}").then()
                .statusCode(200).and()
                .contentType("application/json").and()
                .header("Content-Length","236").and()
                .header("Date",notNullValue()).and()
                .assertThat()
                .body("teachers[0].firstname",is("Alexander"))
                .body("teachers[0].lastname",is("Syrup"))
                .body("teachers[0].gender",equalTo("male"));

    }
    @DisplayName("GET request to teacher/all and chaining")
    @Test
    public void teachersTest(){

        //verify Alexander,Darleen,Sean inside the all teachers
        given()
                .accept(ContentType.JSON)
                .when()
                .get("http://api.cybertektraining.com/teacher/all")
                .then()
                .statusCode(200)
                .and()
                .body("teachers.firstName",hasItems("Alexander","Darleen","Sean"));


    }



}

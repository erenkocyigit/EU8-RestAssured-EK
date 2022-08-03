package com.cydeo.Day7;

import com.cydeo.day6.pojo.Spartan;
import com.cydeo.utilities.SpartanTestBase;
import io.restassured.http.ContentType;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.LinkedHashMap;
import java.util.Map;

import static io.restassured.RestAssured.*;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;
public class PutAndPatchRequestDemo extends SpartanTestBase {


    @DisplayName("PUT request to one spartan for update with Map")
    @Test
    public void PUTRequest(){

        Map<String ,Object> putRequestMap = new LinkedHashMap<>();
        putRequestMap.put("name","Ilon Musk1");
        putRequestMap.put("gender","Male");
        putRequestMap.put("phone",1122334455L);

        given().contentType(ContentType.JSON)
                .body(putRequestMap).log().body().and()
                .pathParam("id", 110).when()
                .put("api/spartans/{id}")
                .then().statusCode(204);

        //send a GET request after update, make sure updated field changed, or the new info matching
        //with requestBody that we send

        Spartan updatedSpartan = given().accept(ContentType.JSON).and()
                .contentType(ContentType.JSON)
                .pathParam("id", 110)
                .when().get("api/spartans/{id}")
                .then().statusCode(200).extract().as(Spartan.class);

        assertThat(updatedSpartan.getName(),is(putRequestMap.get("name")));
        assertThat(updatedSpartan.getGender(),is(putRequestMap.get("gender")));
        assertThat(updatedSpartan.getPhone(),is(putRequestMap.get("phone")));



    }

    @DisplayName("PATCH request to one spartan for partial update with Map")
    @Test
    public void PATCHRequest(){
        //just like post request we have different options to send body, we will go with map
        Map<String,Object> putRequestMap = new LinkedHashMap<>();
        putRequestMap.put("phone",8811111111L);
        putRequestMap.put("name","Peter");

        given().contentType(ContentType.JSON) //hey api I am sending JSON body
                .body(putRequestMap).log().body()
                .and().pathParam("id",110)
                .when().patch("/api/spartans/{id}")
                .then()
                .statusCode(204);

        //send a GET request after update, make sure updated field changed, or the new info matching
        //with requestBody that we send


    }
    @DisplayName("DELETE one spartan")
    @Test
    public void deleteSpartan(){
        int idToDelete= 110;


        given().pathParam("id",idToDelete)
                .when().delete("/api/spartans/{id}")
                .then().statusCode(204);

        //send a get request after you delete make sure you are getting 404

    }




}

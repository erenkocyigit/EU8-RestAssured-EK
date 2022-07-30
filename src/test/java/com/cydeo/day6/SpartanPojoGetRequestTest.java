package com.cydeo.day6;

import com.cydeo.day6.pojo.Search;
import com.cydeo.day6.pojo.Spartan;
import com.cydeo.utilities.SpartanTestBase;
import com.fasterxml.jackson.annotation.JsonAlias;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;
import static io.restassured.RestAssured.*;

public class SpartanPojoGetRequestTest extends SpartanTestBase {

    @DisplayName(("GET spartan and convert it to spartan object"))
    @Test
    public void OneSpartanPojo(){

        Response response = given().accept(ContentType.JSON)
                .and().pathParam("id", 15)
                .when().get("/api/spartans/{id}")
                .then().statusCode(200)
                .log().all()
                .extract().response();

        //deserialize json to pojo
        Spartan spartan15 = response.as(Spartan.class);
        System.out.println(spartan15);
        System.out.println("spartan15.getId() = " + spartan15.getId());
        System.out.println("spartan15.getGender() = " + spartan15.getGender());
        //2nd way jsonpath to deserialize to custom class
        JsonPath jsonPath = response.jsonPath();

        Spartan s15 = jsonPath.getObject("",Spartan.class);

        System.out.println(s15);
        System.out.println("s15.getId() = " + s15.getId());
        System.out.println("s15.getGender() = " + s15.getGender());

    }
    @DisplayName("Get one spartan from search endpoint result and use POJO")
    @Test
    public void spartanSearchWithPojo(){
        ///spartans/search?nameContains=a&gender=Male

        // send get request to above endpoint and save first object with type Spartan POJO

        JsonPath jsonPath = given().accept(ContentType.JSON)
                .and().queryParams("nameContains", "a",
                        "gender", "Male")
                .when().get("/api/spartans/search")
                .then().statusCode(200)
                .extract().jsonPath();

        Spartan s1 = jsonPath.getObject("content[0]", Spartan.class);
        System.out.println("s1 = " + s1);
        System.out.println("s1.getName() = " + s1.getName());
        System.out.println("s1.getGender() = " + s1.getGender());
    }

    @Test
    public void test3(){
        Response response  = given().accept(ContentType.JSON)
                .and().queryParams("nameContains", "a",
                        "gender", "Male")
                .when().get("/api/spartans/search")
                .then().statusCode(200)
                .extract().response();
        Search searchResult = response.as(Search.class);
        System.out.println("searchResult.getContent().get(0).getName() = " + searchResult.getContent().get(0).getName());

    }

    @DisplayName("GET  /spartans/search and save as List<Spartan>")
    @Test
    public void test4(){
        List<Spartan> spartanList = given().accept(ContentType.JSON)
                .and().queryParams("nameContains", "a",
                        "gender", "Male")
                .when().get("/api/spartans/search")
                .then().statusCode(200)
                .extract().jsonPath().getList("content", Spartan.class);

        System.out.println("spartanList.get(1).getName() = " + spartanList.get(1).getName());
    }

}

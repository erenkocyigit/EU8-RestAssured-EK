package com.cydeo.Day11;

import com.cydeo.utilities.ExcelUtil;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.Arrays;
import java.util.List;
import java.util.Map;

import static io.restassured.RestAssured.*;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

import java.util.Map;

public class BookItParametrized {
    public static List<Map<String,String>> getExcelData(){
        //get your file object
        ExcelUtil vytrackFile = new ExcelUtil("src/test/resources/BookItQa3.xlsx","QA3");
        //return sheet as a alist of map
        return vytrackFile.getDataList();

    }


    @ParameterizedTest
    @MethodSource("getExcelData")
    public void bookItTest(Map<String ,String > user){
        System.out.println("user.get(\"email\") = " + user.get("email"));
        System.out.println("user.get(\"password\") = " + user.get("password"));

        given().accept(ContentType.JSON)
                .baseUri("https://cybertek-reservation-api-qa3.herokuapp.com")
                .queryParams(user)
                .when().get("sign")
                .then().statusCode(200)
                .log().body();



    }
}

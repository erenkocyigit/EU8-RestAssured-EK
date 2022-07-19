package com.cydeo.Day4;

import com.cydeo.utilities.HRTestBase;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;

import static io.restassured.RestAssured.get;
import static io.restassured.RestAssured.given;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class ORDSApiWithJsonPath extends HRTestBase {


    @DisplayName("GET request to Countries")
    @Test
    public void test1() {

        Response response = get("/countries");

        //get the second country name with JsonPath

        //to use Json Path we assign response to JsonPath
        JsonPath jsonPath = response.jsonPath();

        String secondCountryName = jsonPath.getString("items[1].country_name");
        System.out.println("secondCountryName = " + secondCountryName);

        //get all country ids
        //items.country_id
        List<String> allCountryIDs = jsonPath.getList("items.country_id");
        System.out.println(allCountryIDs);

        //get all country names where their region id is equal to 2
        List<String > countryNameWithRegionsID2 = jsonPath.getList("items.findAll {it.region_id==3}.country_name");
        System.out.println(countryNameWithRegionsID2);
    }

    @DisplayName("GET requesto /employees with query param")
    @Test
    public void test2(){

        Response response = given().queryParam("limit", 107).when().get("/employees");

        JsonPath jsonPath = response.jsonPath();

        //get me all email of employees who is working as IT_PROG
        List<String > ITPROGEmployeesEmail = jsonPath.getList("items.findAll {it.job_id==\"IT_PROG\"}.email");
        System.out.println("ITPROGEmployeesEmail = " + ITPROGEmployeesEmail);

        //get me first name of employees who is making more than 10000
        List<String> tenTousandMakers = jsonPath.getList("items.findAll {it.salary>10000}.first_name");
        System.out.println("tenTousandMakers = " + tenTousandMakers);

        //get the max salary first_name
        String maxSalaryFirstName = jsonPath.getString("items.max {it.salary}.first_name");
        String maxSalaryWithPathMethod = response.path("items.max {it.salary}.first_name");
        System.out.println("maxSalaryWithPathMethod = " + maxSalaryWithPathMethod);
        System.out.println("maxSalaryFirstName = " + maxSalaryFirstName);


    }

}

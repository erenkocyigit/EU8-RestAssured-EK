package com.cydeo.utilities;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;

import static io.restassured.RestAssured.baseURI;

public abstract class HRTestBase {


    @BeforeAll
    public static void init() {
        //save baseurl inside this variable so that we dont need to type each http method.
        baseURI = "http://3.87.229.116:1000/ords/hr";

        //get ip address from configurations
        String dbUrl = "jdbc:oracle:thin:@3.87.229.116:1521:xe";
        String dbUsername = "SP";
        String dbPassword = "SP";

  //      DBUtils.createConnection(dbUrl, dbUsername, dbPassword);
    }
    @AfterAll
    public static void tearDown(){

   //     DBUtils.destroy();
    }

}

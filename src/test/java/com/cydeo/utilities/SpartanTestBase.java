package com.cydeo.utilities;

import org.junit.jupiter.api.BeforeAll;

import java.net.URI;

import static io.restassured.RestAssured.baseURI;

public abstract class SpartanTestBase {

    @BeforeAll
    public static void init() {
        baseURI = "http://3.87.229.116:8000 ";
    }

}

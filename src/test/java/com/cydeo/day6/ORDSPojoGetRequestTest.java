package com.cydeo.day6;

import com.cydeo.day6.pojo.Link;
import com.cydeo.day6.pojo.Region;
import com.cydeo.utilities.HRTestBase;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import org.junit.jupiter.api.Test;


import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;
import static io.restassured.RestAssured.*;

public class ORDSPojoGetRequestTest extends HRTestBase {

    @Test
    public void regionTest(){

        Region region1 = get("/regions").then().statusCode(200).extract().jsonPath().getObject("items[0]",Region.class);

      //  Region region1 = jsonPath.getObject("items[0]", Region.class);
        System.out.println(region1);

        System.out.println("region1.getRegion_id() = " + region1.getRegion_id());
        System.out.println("region1.getRegion_name() = " + region1.getRegion_name());
        System.out.println("region1.getLinks() = " + region1.getLinks());
//        Link link1 = region1.getLinks().get(0);
//        System.out.println("link1.getHref() = " + link1.getHref());


    }


}
